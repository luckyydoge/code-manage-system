package edu.csu.codemanagesystem.domain.admin.service.course;

import edu.csu.codemanagesystem.domain.admin.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.admin.repository.IAdminRepository;
import edu.csu.codemanagesystem.domain.admin.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceService implements ICourseService {

    final private IAdminRepository repository;

    public CourseServiceService(IAdminRepository repository) {
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
