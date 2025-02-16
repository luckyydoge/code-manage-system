package edu.csu.codemanagesystem.domain.admin.service;

import edu.csu.codemanagesystem.domain.admin.model.entity.CourseEntity;

import java.util.List;

public interface ICourseService {
    List<CourseEntity> queryCourse(CourseEntity courseReqFactor);

    Boolean createCourse(CourseEntity course);

}
