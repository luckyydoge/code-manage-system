package edu.csu.codemanagesystem.domain.student.service;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.StudentJobEntity;

import java.util.List;

public interface IStudentService {
    List<StudentEntity> queryStudentByFactor(StudentEntity factor);

    List<StudentJobEntity> queryStudentJobByFactor(StudentJobEntity factor);
}
