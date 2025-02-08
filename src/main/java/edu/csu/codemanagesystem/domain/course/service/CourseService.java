package edu.csu.codemanagesystem.domain.course.service;

import edu.csu.codemanagesystem.domain.course.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.course.repository.ICourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseService implements ICourse {

    final private ICourseRepository repository;

    public CourseService(ICourseRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<CourseEntity> queryCourse(CourseEntity courseReqFactor) {
        List<CourseEntity> courseEntityList = repository.queryCourseByFactor(courseReqFactor);
        log.info("query course");
        return courseEntityList;
    }

    @Override
    public Boolean createCourse(CourseEntity course) {
//        if (course.getCourseId() == null
//        || course.getStartTime() == null || course.getEndTime() == null) {
//            return false;
//        }
        repository.createCourse(course);
        log.info("create course {}", course);
        return true;

    }


}
