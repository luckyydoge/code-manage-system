package edu.csu.codemanagesystem.domain.teacher.service.job;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.StudentJobEntity;
import edu.csu.codemanagesystem.domain.teacher.repository.ITeacherRepository;
import edu.csu.codemanagesystem.domain.teacher.service.IEmailService;
import edu.csu.codemanagesystem.domain.teacher.service.IJobService;
import edu.csu.codemanagesystem.infrastructure.po.Job;
import edu.csu.codemanagesystem.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JobService implements IJobService {

    private final ITeacherRepository teacherRepository;
    private final IEmailService emailService;
    private final String uploadPath = System.getProperty("user.dir")  + "/uploads";

    public JobService(ITeacherRepository teacherRepository, IEmailService emailService) {
        this.teacherRepository = teacherRepository;
        this.emailService = emailService;
    }

    @Override
    public Boolean createJob(JobEntity jobEntity) {
        List<StudentEntity> studentEntityList = teacherRepository.queryStudentByClassId(jobEntity.getClassId());
        teacherRepository.insertJobAndStudentIntoDatabase(jobEntity, studentEntityList);
        String courseName = teacherRepository.queryCourseNameByCourseId(jobEntity.getCourseId());
        emailService.sendEmail(studentEntityList, jobEntity, courseName);
        return true;
    }

    @Override
    public List<JobEntity> queryJobByFactor(JobEntity jobEntityFactor) {
        List<JobEntity> jobEntitiesList = teacherRepository.queryJobByFactor(jobEntityFactor);
        log.info("queryClassByFactor factor:{}, jobEntitiesList:{}", jobEntityFactor, jobEntitiesList);
        return jobEntitiesList;
    }

    @Override
    public List<JobEntity> queryJobByStudentId(Long studentId) {
        List<Long> jobIdList = teacherRepository.queryJobIdByStudentId(studentId);
        List<JobEntity> jobEntityList = teacherRepository.queryJobByJobId(jobIdList);
        return jobEntityList;
    }

    @Override
    public List<JobEntity> queryJobByStudentJobFactor(StudentJobEntity factor) {
        List<Long> jobIdList = teacherRepository.queryJobIdByStudentJobFactor(factor);
        if (jobIdList.isEmpty()) {
            return List.of();
        }
        List<JobEntity> jobEntityList = teacherRepository.queryJobByJobId(jobIdList);
        return jobEntityList;
    }

    @Override
    public Boolean submitJob(MultipartFile file, StudentJobEntity studentJobEntity) {
        studentJobEntity.setStatus("submitted");
        teacherRepository.updateStudentJob(studentJobEntity);
        Long currentTimeMillis = System.currentTimeMillis();
        String filePath = uploadPath + "/" + studentJobEntity.getJobId() + "/" + studentJobEntity.getStudentId() + "/" +
            file.getOriginalFilename() + "_" + currentTimeMillis;
        try {
            FileUtils.saveInputStreamToFile(file.getInputStream(), filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
