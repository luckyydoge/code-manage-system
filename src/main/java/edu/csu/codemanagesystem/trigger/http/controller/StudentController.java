package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.service.IJobService;
import edu.csu.codemanagesystem.type.Response;
import edu.csu.codemanagesystem.type.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class StudentController {

    @Autowired
    IJobService jobService;

    @GetMapping("/student/queryJobByStudentId")
    public Response<List<JobEntity>> queryJobByStudentId(@RequestParam("studentId") Long studentId) {
        List<JobEntity> jobEntityList = jobService.queryJobByStudentId(studentId);
        return Response.<List<JobEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(jobEntityList)
                .build();
    }
}
