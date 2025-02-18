package edu.csu.codemanagesystem.domain.teacher.service.job;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.repository.ITeacherRepository;
import edu.csu.codemanagesystem.domain.teacher.service.IEmailService;
import edu.csu.codemanagesystem.domain.teacher.service.IJobService;
import edu.csu.codemanagesystem.infrastructure.po.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JobService implements IJobService {

    private final ITeacherRepository teacherRepository;
    private final IEmailService emailService;

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

}
