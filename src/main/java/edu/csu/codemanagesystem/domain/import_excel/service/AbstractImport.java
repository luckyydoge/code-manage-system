package edu.csu.codemanagesystem.domain.import_excel.service;

import cn.idev.excel.FastExcel;
import edu.csu.codemanagesystem.domain.import_excel.repository.IImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public abstract class AbstractImport {

    private final IImportRepository importRepository;

    public AbstractImport(IImportRepository importRepository) {
        this.importRepository = importRepository;
    }

    public Boolean importExcel(MultipartFile file) {
//        FastExcel.read
        return false;
    }
}
