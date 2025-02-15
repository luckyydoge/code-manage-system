package edu.csu.codemanagesystem.domain.import_excel.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;

import java.util.List;

public interface IImportRepository {
    int queryTeacherCount();

    void insertTeacherBatch(List<TeacherEntity> cacheList);

    void insertTeacherBatchIntoUsers(List<TeacherEntity> cacheList);

    int queryStudentCount();

    void insertStudentBatch(List<StudentEntity> cacheList);

    void insertStudentBatchToUsers(List<StudentEntity> cacheList);

    void insertStudentBatchToStudentClass(List<StudentEntity> cacheList, Long classId);
}
