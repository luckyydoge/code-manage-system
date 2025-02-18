package edu.csu.codemanagesystem.domain.student.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;

import java.util.List;

public interface IStudentRepository {
    List<StudentEntity> queryStudentByFactor(StudentEntity factor);
}
