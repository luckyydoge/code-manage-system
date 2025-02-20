package edu.csu.codemanagesystem.domain.teacher.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.StudentJobEntity;

import java.util.List;

public interface ITeacherRepository {
    void createClass(ClassEntity classEntity);

    List<StudentEntity> queryStudentByClassId(Long classId);

    String queryCourseNameByCourseId(Long courseId);

    void insertJobIntoJobTable(JobEntity jobEntity);


    void insertJobAndStudentIntoDatabase(JobEntity jobEntity, List<StudentEntity> studentEntityList);

    List<ClassEntity> queryClassByFactor(ClassEntity classEntityFactor);

    List<JobEntity> queryJobByFactor(JobEntity jobEntityFactor);

    List<Long> queryJobIdByStudentId(Long studentId);

    List<JobEntity> queryJobByJobId(List<Long> jobIdList);

    List<Long> queryJobIdByStudentJobFactor(StudentJobEntity factor);

    List<ClassEntity> queryClassBuStudentId(Long studentId);

    void updateStudentJob(StudentJobEntity studentJobEntity);

    List<StudentEntity> queryStudentByFactor(StudentEntity studentEntityBuilder);

    TeacherEntity queryTeacherByClassId(Long classId);

    void insertStudentJob(Long jobId, List<StudentEntity> studentEntityList);

    void updateJobById(JobEntity jobEntity);
}
