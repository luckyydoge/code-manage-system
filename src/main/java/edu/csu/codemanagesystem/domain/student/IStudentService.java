package edu.csu.codemanagesystem.domain.student;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;

import java.util.List;

public interface IStudentService {
    List<StudentEntity> queryStudentByFactor(StudentEntity factor);
}
