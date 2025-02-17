package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.admin.service.IBackupService;
import edu.csu.codemanagesystem.domain.admin.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.admin.service.ICourseService;
import edu.csu.codemanagesystem.domain.admin.service.ISemesterService;
import edu.csu.codemanagesystem.domain.admin.service.ITeacherManageService;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.import_excel.service.IImportExcel;
import edu.csu.codemanagesystem.domain.import_excel.service.ImportServiceFactory;
import edu.csu.codemanagesystem.domain.admin.model.entity.SemesterEntity;
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
    IBackupService backupService;

    @Autowired
    ICourseService courseService;

    @Autowired
    private ImportServiceFactory importServiceFactory;

    @Autowired
    ISemesterService semesterService;

    @Autowired
    ITeacherManageService teacherService;

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

    @GetMapping("/admin/queryCurrentSemester")
    public Response<SemesterEntity> queryCurrentSemester() {
        SemesterEntity semester = semesterService.queryCurrentSemester();
        return Response.<SemesterEntity>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(semester)
                .build();
    }

    @GetMapping("/admin/setCurrentSemester")
    public Response<Boolean> setCurrentSemester(@RequestParam Long semesterId) {
        Boolean success = semesterService.setCurrentSemester(semesterId);
        return returnResponseByBoolean(success);
    }

    @PostMapping("/admin/createTeacher")
    public Response<Boolean> createTeacher(@RequestBody TeacherEntity teacher) {
        Boolean success = teacherService.addTeacher(teacher);
        return returnResponseByBoolean(success);

    }

    @PostMapping("/admin/importTeacherInfo")
    public Response<Boolean> importTeacherInfo(@RequestParam MultipartFile file) {
        IImportExcel teacherImportService = importServiceFactory.getImportExcel(ImportServiceFactory.ImportServiceType.TEACHER);
        Boolean success;
        try {
            success = teacherImportService.ImportExcel(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return Response.<Boolean>builder()
                    .code(ResponseCode.FAILURE.getCode())
                    .info(ResponseCode.FAILURE.getInfo())
                    .build();
        }
        return returnResponseByBoolean(success);
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

    @GetMapping("/admin/queryAllTeacher")
    public Response<List<TeacherEntity>> queryAllTeacher() {
        List<TeacherEntity> teacherList = teacherService.queryAllTeachers();
        return Response.<List<TeacherEntity>>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(teacherList)
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

    @GetMapping("/admin/setInterval")
    public Response<Boolean> setInterval(@RequestParam Long interval) {
        Boolean success =  backupService.setInterval(interval);
        return returnResponseByBoolean(success);
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

    private Response<Boolean> returnResponseByBoolean(Boolean success) {
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
}
