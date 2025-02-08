package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.semester.model.entity.SemesterEntity;
import edu.csu.codemanagesystem.domain.semester.service.SemesterService;
import edu.csu.codemanagesystem.type.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SemesterController {

    @Autowired
    SemesterService semesterService;

    @GetMapping("/admin/queryAllSemester")
    public Response<List<SemesterEntity>> queryAllSemester() {
        List<SemesterEntity> semesters = semesterService.queryAllSemester();
        return Response.<List<SemesterEntity>>builder()
                .code("000")
                .info("")
                .data(semesters)
                .build();
    }

    @PostMapping("/admin/createSemester")
    public Response<Boolean> createSemester(@RequestBody SemesterEntity semester) {
        semesterService.createSemester(semester);
        return Response.<Boolean>builder()
                .code("000")
                .info("")
                .data(true)
                .build();
    }
}
