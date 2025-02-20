package edu.csu.codemanagesystem.domain.teacher.service;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.model.valobj.DuplicateCheckRes;

import java.util.List;

public interface IEmailService {
    void sendEmail(List<StudentEntity> students, JobEntity job, String courseName);

    void sendDuplicateCheckEmail(List<DuplicateCheckRes> duplicateCheckResList, String email);
}
