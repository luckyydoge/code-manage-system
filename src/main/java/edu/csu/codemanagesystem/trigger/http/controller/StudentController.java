package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.student.IStudentService;
import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.StudentJobEntity;
import edu.csu.codemanagesystem.domain.teacher.model.valobj.StudentJobSubmitTextVO;
import edu.csu.codemanagesystem.domain.teacher.service.IClassManageService;
import edu.csu.codemanagesystem.domain.teacher.service.IJobService;
import edu.csu.codemanagesystem.type.Response;
import edu.csu.codemanagesystem.type.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
public class StudentController {

    @Autowired
    IJobService jobService;

    @Autowired
    IStudentService studentService;

    @Autowired
    IClassManageService classManageService;

    @PostMapping("/student/queryJobByStudentJobFactor")
    public Response<List<JobEntity>> queryJobByStudentJobFactor(@RequestBody StudentJobEntity factor) {
        List<JobEntity> jobEntityList = jobService.queryJobByStudentJobFactor(factor);
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

    @GetMapping("/api/queryClassByStudentId")
    public Response<List<ClassEntity>> queryClassByStudentId(@RequestParam Long studentId) {
        List<ClassEntity> result = classManageService.queryClassByStudentId(studentId);
        return Response.<List<ClassEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(result)
                .build();
    }

    @PostMapping("/api/submitJob")
    public Response<Boolean> submitJob(@RequestParam MultipartFile file, @RequestParam Long studentId, @RequestParam Long jobId) {
        Boolean result = jobService.submitJob(file, new StudentJobEntity((long) jobId, (long) studentId, "submitted"));
        return Response.<Boolean>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(result)
                .build();
    }

    @PostMapping("/api/submitJobText")
    public Response<Boolean> submitJobText(@RequestBody StudentJobSubmitTextVO studentJobSubmitTextVO) {
        Long studentId = studentJobSubmitTextVO.getStudentId();
        Long jobId = studentJobSubmitTextVO.getJobId();
        String text = studentJobSubmitTextVO.getJobText();
        Boolean result = jobService.submitJob(text, new StudentJobEntity((long) jobId, (long) studentId, "submitted"));
        return Response.<Boolean>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(result)
                .build();
    }

}
