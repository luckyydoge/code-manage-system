package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.repository.ITeacherRepository;
import edu.csu.codemanagesystem.infrastructure.dao.*;
import edu.csu.codemanagesystem.infrastructure.po.ClassPO;
import edu.csu.codemanagesystem.infrastructure.po.Job;
import edu.csu.codemanagesystem.infrastructure.po.Student;
import edu.csu.codemanagesystem.infrastructure.po.StudentJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository implements ITeacherRepository {

    @Autowired
    IStudentJobDao studentJobDao;

    @Autowired
    IJobDao jobDao;

    @Autowired
    IClassDao classDao;

    @Autowired
    IStudentClassDao studentClassDao;

    @Autowired
    IStudentDao studentDao;

    @Override
    public void createClass(ClassEntity classEntity) {
        long count = classDao.queryClassCount();
        ClassPO classPo = new ClassPO();
        classPo.setName(classEntity.getName());
        classPo.setTeacherId(classEntity.getTeacherId());
        classPo.setSemesterId(classEntity.getSemesterId());
        classPo.setPeopleCount(classEntity.getPeopleCount());
        classPo.setCourseId(classEntity.getCourseId());
        classPo.setClassId(count + 400000 + 1);
        classDao.createClass(classPo);
    }

    @Override
    public List<StudentEntity> queryStudentByClassId(Long classId) {
        List<Long> studentIdList = studentClassDao.queryStudentIdByClassId(classId);
        List<Student> studentList = studentDao.queryStudentByStudentIdList(studentIdList);
        List<StudentEntity> studentEntityList = new ArrayList<>();
        studentList.forEach(student -> {
            StudentEntity studentEntity = StudentEntity.builder()
                    .studentId(student.getStudentId())
                    .name(student.getName())
                    .sex(student.getSex())
                    .email(student.getEmail())
                    .administrativeClass(student.getAdministrativeClass())
                    .phoneNumber(student.getPhoneNumber())
                    .build();
            studentEntityList.add(studentEntity);
        });
        return studentEntityList;
    }

    @Override
    public String queryCourseNameByCourseId(Long courseId) {

        return classDao.queryCourseNameByCourseId(courseId);
    }

    @Override
    public void insertJobIntoJobTable(JobEntity jobEntity) {
        long count = jobDao.queryJobCount();
        Job job = new Job();
        job.setJobId(count + 1);
        jobEntity.setJobId(count + 1);
        job.setCourseId(jobEntity.getCourseId());
        job.setClassId(jobEntity.getClassId());
        job.setContent(jobEntity.getContent());
        job.setTitle(jobEntity.getTitle());
        job.setStartTime(jobEntity.getStartTime());
        job.setEndTime(jobEntity.getEndTime());
        jobDao.insertJob(job);
    }

    @Override
    public void insertStudentJob(Long jobId, List<Long> studentIdList) {
        List<StudentJob> studentJobList = new ArrayList<>();
        studentIdList.forEach(studentId -> {
            StudentJob studentJob = new StudentJob();
            studentJob.setJobId(jobId);
            studentJob.setStudentId(studentId);
            studentJobList.add(studentJob);
        });
        studentJobDao.insertStudentJobBatch(studentJobList);

    }

    @Override
    @Transactional
    public void insertJobAndStudentIntoDatabase(JobEntity jobEntity, List<StudentEntity> studentEntityList) {
        this.insertJobIntoJobTable(jobEntity);
        List<Long> studentIdList = new ArrayList<>();
        studentEntityList.forEach(studentEntity -> {
            studentIdList.add(studentEntity.getStudentId());
        });
        this.insertStudentJob(jobEntity.getJobId(), studentIdList);
    }
}

