package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.course.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.course.repository.ICourseRepository;
import edu.csu.codemanagesystem.infrastructure.dao.ICourseDao;
import edu.csu.codemanagesystem.infrastructure.po.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository implements ICourseRepository {

    @Autowired
    ICourseDao courseDao;

    @Override
    public List<CourseEntity> queryCourseByFactor(CourseEntity courseReqFactor) {
        Course courseReq = new Course();
        courseReq.setCourseId(courseReqFactor.getCourseId());
        courseReq.setName(courseReqFactor.getName());
        courseReq.setStartTime(courseReqFactor.getStartTime());
        courseReq.setEndTime(courseReqFactor.getEndTime());
        List<Course> courseList = courseDao.queryCourseByFactor(courseReq);
        List<CourseEntity> courseEntityList = new ArrayList<>();
        courseList.forEach(course -> {
            CourseEntity courseEntity = CourseEntity.builder()
                    .courseId(course.getCourseId())
                    .name(course.getName())
                    .status(course.getStatus())
                    .startTime(course.getStartTime())
                    .endTime(course.getEndTime())
                    .build();
            courseEntityList.add(courseEntity);
        });

        return courseEntityList;
    }

    @Override
    public void createCourse(CourseEntity courseEntity) {
        Course course = new Course();
        course.setCourseId(courseEntity.getCourseId());
        course.setName(courseEntity.getName());
        course.setStartTime(courseEntity.getStartTime());
        course.setEndTime(courseEntity.getEndTime());
        courseDao.createCourse(course);
    }
}
