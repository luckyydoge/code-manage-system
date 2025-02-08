package edu.csu.codemanagesystem.domain.course.repository;

import edu.csu.codemanagesystem.domain.course.model.entity.CourseEntity;

import java.util.List;

public interface ICourseRepository {
    List<CourseEntity> queryCourseByFactor(CourseEntity courseReqFactor);

    void createCourse(CourseEntity course);
}
