package edu.csu.codemanagesystem.domain.import_excel.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;

import java.util.List;

public interface IImportRepository {
    int queryTeacherCount();

    void insertTeacherBatch(List<TeacherEntity> cacheList);

    void insertTeacherBatchIntoUsers(List<TeacherEntity> cacheList);
}
