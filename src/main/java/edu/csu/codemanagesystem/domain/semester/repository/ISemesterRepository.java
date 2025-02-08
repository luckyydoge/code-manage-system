package edu.csu.codemanagesystem.domain.semester.repository;

import edu.csu.codemanagesystem.domain.semester.model.entity.SemesterEntity;

import java.util.List;

public interface ISemesterRepository {
    List<SemesterEntity> queryAllSemester();

    void createSemester(SemesterEntity semester);
}
