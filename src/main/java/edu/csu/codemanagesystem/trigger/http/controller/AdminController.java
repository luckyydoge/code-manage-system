package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.admin.service.IBackup;
import edu.csu.codemanagesystem.domain.admin.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.admin.service.course.CourseService;
import edu.csu.codemanagesystem.domain.import_excel.service.IImportExcel;
import edu.csu.codemanagesystem.domain.import_excel.service.ImportServiceFactory;
import edu.csu.codemanagesystem.domain.admin.model.entity.SemesterEntity;
import edu.csu.codemanagesystem.domain.admin.service.semester.SemesterService;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@Slf4j
public class AdminController {

    @Autowired
    IBackup backupService;

    @Autowired
    CourseService courseService;

    @Autowired
    private ImportServiceFactory importServiceFactory;

    @Autowired
    SemesterService semesterService;

    @GetMapping("/admin/queryAllSemester")
    public Response<List<SemesterEntity>> queryAllSemester() {
        List<SemesterEntity> semesterList = semesterService.queryAllSemester();
        return Response.<List<SemesterEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(semesterList)
                .build();
    }

    @PostMapping("/admin/createSemester")
    public Response<Boolean> createSemester(@RequestBody SemesterEntity semester) {
        semesterService.createSemester(semester);
        return Response.<Boolean>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(true)
                .build();
    }

    @GetMapping("/admin/setCurrentSemester")
    public Response<Boolean> setCurrentSemester(@RequestParam Long semesterId) {
        Boolean success = semesterService.setCurrentSemester(semesterId);
        if (success) {
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
        }
        return Response.<Boolean>builder()
                .code(ResponseCode.FAILURE.getCode())
                .info(ResponseCode.FAILURE.getInfo())
                .data(false)
                .build();

    }

    @PostMapping("/admin/importTeacherInfo")
    public Response<String> importTeacherInfo(@RequestParam MultipartFile file) {
        IImportExcel teacherImportService = importServiceFactory.getImportExcel(ImportServiceFactory.ImportServiceType.TEACHER);
        Boolean success = null;
        try {
            success = teacherImportService.ImportExcel(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return Response.<String>builder()
                    .code(ResponseCode.FAILURE.getCode())
                    .info(ResponseCode.FAILURE.getInfo())
                    .build();
        }
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


    @PostMapping("/admin/queryCourse")
    public Response<List<CourseEntity>> queryCourse(@RequestBody CourseEntity courseReqFactor) {
        List<CourseEntity> result = courseService.queryCourse(courseReqFactor);
        return Response.<List<CourseEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(result)
                .build();
    }

    @PostMapping("/admin/createCourse")
    public Response<Boolean> createCourse(@RequestBody CourseEntity course) {
        courseService.createCourse(course);
        return Response.<Boolean>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(true)
                .build();
    }

    @GetMapping("/admin/backupDatabase")
    public Response<Boolean> backupDatabase() {
        backupService.backup();
        return Response.<Boolean>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .build();
    }

    @GetMapping("/admin/downloadBackupFile")
    public void downloadBackupFile(HttpServletResponse response) {
        Path filepath = Paths.get("./docs/sql/backup.sql");
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
}
