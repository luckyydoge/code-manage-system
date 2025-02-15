package edu.csu.codemanagesystem.domain.import_excel.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface IImportExcel<T> {
    Boolean ImportExcel(InputStream inputStream);
}
