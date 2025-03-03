package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICourseDao {
    List<Course> queryCourseByFactor(Course course);

    void createCourse(Course course);

    List<Course> queryUpcomingCourseByFactor(Course courseReq);

    List<Course> queryOngoingCourseByFactor(Course courseReq);

    List<Course> queryCompletedCourseByFactor(Course courseReq);

    long queryCourseCount();
}
