package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.student.IStudentService;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.service.IJobService;
import edu.csu.codemanagesystem.type.Response;
import edu.csu.codemanagesystem.type.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class StudentController {

    @Autowired
    IJobService jobService;

    @Autowired
    IStudentService studentService;

    @GetMapping("/student/queryJobByStudentId")
    public Response<List<JobEntity>> queryJobByStudentId(@RequestParam("studentId") Long studentId) {
        List<JobEntity> jobEntityList = jobService.queryJobByStudentId(studentId);
        return Response.<List<JobEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(jobEntityList)
                .build();
    }

    @PostMapping("/api/queryStudentByFactor")
    public Response<List<StudentEntity>> queryStudentByFactor(@RequestBody StudentEntity factor) {
        List<StudentEntity> result = studentService.queryStudentByFactor(factor);
        return Response.<List<StudentEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(result)
                .build();
    }
}
