package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.import_excel.service.IImportExcel;
import edu.csu.codemanagesystem.domain.import_excel.service.ImportServiceFactory;
import edu.csu.codemanagesystem.domain.import_excel.service.impl.ImportStudentInfo;
import edu.csu.codemanagesystem.type.Response;
import edu.csu.codemanagesystem.type.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RestController
public class ImportController {
    @Autowired
    private ImportServiceFactory importServiceFactory;

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
