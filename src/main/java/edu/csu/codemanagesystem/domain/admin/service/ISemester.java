package edu.csu.codemanagesystem.domain.admin.service;

import edu.csu.codemanagesystem.domain.admin.model.entity.SemesterEntity;

import java.util.List;

public interface ISemester {
    List<SemesterEntity> queryAllSemester();

    Boolean createSemester(SemesterEntity semester);

    Boolean setCurrentSemester(Long semesterId);
}
