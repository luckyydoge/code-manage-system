package edu.csu.codemanagesystem.domain.import_excel.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImportServiceFactory {

    private final Map<String, IImportExcel> importExcelMap;

    public ImportServiceFactory(Map<String, IImportExcel> importExcelMap) {
        this.importExcelMap = importExcelMap;
    }

    public IImportExcel getImportExcel(ImportServiceType importServiceType) {
        return importExcelMap.get(importServiceType.getType());

    }


    @Getter
    @AllArgsConstructor
    public enum ImportServiceType {

        STUDENT("student_import_service"),
        TEACHER("teacher_import_service"),
        ;
        private String type;

    }
}
