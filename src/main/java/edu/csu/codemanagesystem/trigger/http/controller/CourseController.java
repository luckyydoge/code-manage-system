package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.course.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.course.service.CourseService;
import edu.csu.codemanagesystem.type.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/admin/queryCourse")
    public Response<List<CourseEntity>> queryCourse(@RequestBody CourseEntity courseReqFactor) {
        List<CourseEntity> result = courseService.queryCourse(courseReqFactor);
        return Response.<List<CourseEntity>>builder()
                .code("000")
                .info("")
                .data(result)
                .build();
    }

    @PostMapping("/admin/createCourse")
    public Response<Boolean> createCourse(@RequestBody CourseEntity course) {
        courseService.createCourse(course);
        return Response.<Boolean>builder()
                .code("000")
                .info("")
                .data(true)
                .build();
    }
}
