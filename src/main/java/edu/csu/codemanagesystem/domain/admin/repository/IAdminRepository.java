package edu.csu.codemanagesystem.domain.admin.repository;

import edu.csu.codemanagesystem.domain.admin.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.admin.model.entity.SemesterEntity;

import java.util.List;

public interface IAdminRepository {
    List<SemesterEntity> queryAllSemester();

    void createSemester(SemesterEntity semester);

    Boolean setCurrentSemester(Long semesterId);

    List<CourseEntity> queryCourseByFactor(CourseEntity courseReqFactor);

    void createCourse(CourseEntity course);
}
