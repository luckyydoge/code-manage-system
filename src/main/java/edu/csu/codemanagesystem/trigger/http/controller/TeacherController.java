package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.import_excel.service.IImportExcel;
import edu.csu.codemanagesystem.domain.import_excel.service.ImportServiceFactory;
import edu.csu.codemanagesystem.domain.import_excel.service.impl.ImportStudentInfo;
import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.service.IClassManageService;
import edu.csu.codemanagesystem.domain.teacher.service.IJobService;
import edu.csu.codemanagesystem.type.Response;
import edu.csu.codemanagesystem.type.ResponseCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@Slf4j
public class TeacherController {

    @Autowired
    IJobService jobService;

    @Autowired
    private ImportServiceFactory importServiceFactory;

    @Autowired
    private IClassManageService classManageService;

    @PostMapping("/teacher/importStudentInfo")
    public Response<String> importStudentInfo(@RequestParam MultipartFile file, @RequestParam Long classId) {
        IImportExcel studentImportService = importServiceFactory.getImportExcel(ImportServiceFactory.ImportServiceType.STUDENT);
        ImportStudentInfo t = (ImportStudentInfo) studentImportService;
        t.setClassId(classId);
        Boolean success = null;
        try {
            success = studentImportService.ImportExcel(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return Response.<String>builder()
                    .code(ResponseCode.FAILURE.getCode())
                    .info(ResponseCode.FAILURE.getInfo())
                    .build();
        }
        return returnStringResponseByBoolean(success);
    }

    @PostMapping("/teacher/createClass")
    public Response<String> createClass(@RequestBody ClassEntity classEntity) {
        Boolean success = classManageService.createClass(classEntity);
        return returnStringResponseByBoolean(success);
    }

    @PostMapping("/teacher/createJob")
    public Response<String> createJob(@RequestBody JobEntity jobEntity) {
        jobService.createJob(jobEntity);
        return returnStringResponseByBoolean(true);

    }

    @PostMapping("/teacher/queryClassByFactor")
    public Response<List<ClassEntity>> queryClassByFactor(@RequestBody ClassEntity classEntity) {
        List<ClassEntity> classEntityList = classManageService.queryClassByFactor(classEntity);
        return Response.<List<ClassEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(classEntityList).build();
    }

    @PostMapping("/teacher/queryJobByFactor")
    public Response<List<JobEntity>> queryJobByFactor(@RequestBody JobEntity jobEntity) {
        List<JobEntity> jobEntityList = jobService.queryJobByFactor(jobEntity);
        return Response.<List<JobEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(jobEntityList).build();
    }

    @GetMapping("/api/queryJobFileByStudentIdAndJobId")
    public void queryJobFileByStudentIdAndJobId(@RequestParam Long studentId, @RequestParam Long jobId, HttpServletResponse response) {
        String filePath = jobService.queryJobFilePathByStudentIdAndJobId(studentId, jobId);
        if (filePath == null) {
            return;
        }
        Path filepath = Paths.get(filePath);
        try {
            Resource resource = new UrlResource(filepath.toUri());
            if (resource.exists() && resource.isReadable()) {
                try (InputStream inputStream = resource.getInputStream()) {
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response<String> returnStringResponseByBoolean(Boolean success) {
        if (success) {
            return Response.<String>builder()
                    .info(ResponseCode.SUCCESS.getInfo())
                    .code(ResponseCode.SUCCESS.getCode())
                    .build();
        }
        return Response.<String>builder()
                .code(ResponseCode.FAILURE.getCode())
                .info(ResponseCode.FAILURE.getInfo())
                .build();
    }

}
