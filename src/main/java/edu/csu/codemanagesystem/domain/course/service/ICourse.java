package edu.csu.codemanagesystem.domain.course.service;

import edu.csu.codemanagesystem.domain.course.model.entity.CourseEntity;

import java.util.List;

public interface ICourse {
    List<CourseEntity> queryCourse(CourseEntity courseReqFactor);

    Boolean createCourse(CourseEntity course);

}
