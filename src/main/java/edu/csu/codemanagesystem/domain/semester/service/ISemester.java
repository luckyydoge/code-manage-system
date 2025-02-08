package edu.csu.codemanagesystem.domain.semester.service;

import edu.csu.codemanagesystem.domain.semester.model.entity.SemesterEntity;

import java.util.List;

public interface ISemester {
    List<SemesterEntity> queryAllSemester();

    Boolean createSemester(SemesterEntity semester);
}
