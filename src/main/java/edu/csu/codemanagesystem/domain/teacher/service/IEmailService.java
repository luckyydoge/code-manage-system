package edu.csu.codemanagesystem.domain.teacher.service;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;

import java.util.List;

public interface IEmailService {
    void sendEmail(List<StudentEntity> students, JobEntity job, String courseName);
}
