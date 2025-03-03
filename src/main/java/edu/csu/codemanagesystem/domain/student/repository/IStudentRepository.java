package edu.csu.codemanagesystem.domain.student.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.StudentJobEntity;

import java.util.List;

public interface IStudentRepository {
    List<StudentEntity> queryStudentByFactor(StudentEntity factor);

    List<StudentJobEntity> queryStudentJobByFactor(StudentJobEntity factor);
}
