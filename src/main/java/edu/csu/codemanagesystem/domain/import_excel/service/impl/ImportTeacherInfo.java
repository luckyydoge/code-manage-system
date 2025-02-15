package edu.csu.codemanagesystem.domain.import_excel.service.impl;

import cn.idev.excel.FastExcel;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import cn.idev.excel.util.ListUtils;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.import_excel.repository.IImportRepository;
import edu.csu.codemanagesystem.domain.import_excel.service.AbstractImport;
import edu.csu.codemanagesystem.domain.import_excel.service.IImportExcel;
import edu.csu.codemanagesystem.infrastructure.repository.ImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@Service
@Component("teacher_import_service")
public class ImportTeacherInfo extends AbstractImport<TeacherEntity> {

    public ImportTeacherInfo(ImportRepository importRepository) {
        super(importRepository);
    }

    private long baseCount = 200000;

    @Override
    protected int getCount() {
        return importRepository.queryTeacherCount();
    }

    @Override
    protected void setId(TeacherEntity entity, int count) {
        entity.setTeacherId(count + baseCount);
    }

    @Override
    protected Class<TeacherEntity> getClazz() {
        return TeacherEntity.class;
    }

    protected void saveData(List<TeacherEntity> cacheList) {
        importRepository.insertTeacherBatch(cacheList);
        importRepository.insertTeacherBatchIntoUsers(cacheList);
        cacheList.clear();
    }
}
