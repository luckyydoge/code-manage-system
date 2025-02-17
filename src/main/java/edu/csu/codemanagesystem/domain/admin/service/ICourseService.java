package edu.csu.codemanagesystem.domain.admin.service;

import edu.csu.codemanagesystem.domain.admin.model.entity.CourseEntity;

import java.util.List;

public interface ICourseService {
    List<CourseEntity> queryCourse(CourseEntity courseReqFactor);

    Boolean createCourse(CourseEntity course);

    List<CourseEntity> queryUpcomingCourse(CourseEntity courseReqFactor);

    List<CourseEntity> queryOngoingCourse(CourseEntity courseReqFactor);

    List<CourseEntity> queryCompletedCourse(CourseEntity courseReqFactor);

}
