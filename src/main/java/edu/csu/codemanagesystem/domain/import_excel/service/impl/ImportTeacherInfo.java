package edu.csu.codemanagesystem.domain.import_excel.service.impl;

import cn.idev.excel.FastExcel;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import cn.idev.excel.util.ListUtils;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.import_excel.repository.IImportRepository;
import edu.csu.codemanagesystem.domain.import_excel.service.IImportExcel;
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
public class ImportTeacherInfo implements IImportExcel {

    private final IImportRepository importRepository;

    public ImportTeacherInfo(IImportRepository importRepository) {
        this.importRepository = importRepository;
    }

    @Override
    public Boolean ImportExcel(InputStream inputStream) {
        int batchCount = 50;
        List<TeacherEntity> cacheList = new ArrayList<>(55);
        log.info("input stream {}", inputStream.toString());
        FastExcel.read(inputStream, TeacherEntity.class, new ReadListener<TeacherEntity>() {
            int teacherCount = importRepository.queryTeacherCount();

            @Override
            public void invoke(TeacherEntity teacherEntity, AnalysisContext analysisContext) {
                log.info("get teacher {}", teacherEntity.toString());
                teacherEntity.setTeacherId((long) (200000 + teacherCount));
                teacherCount += 1;
                cacheList.add(teacherEntity);
                if (cacheList.size() >= batchCount) {
                    saveData(cacheList);
                }

            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                saveData(cacheList);
            }
        }).sheet().doRead();

        return true;
    }

    private void saveData(List<TeacherEntity> cacheList) {
        importRepository.insertTeacherBatch(cacheList);
        importRepository.insertTeacherBatchIntoUsers(cacheList);
        cacheList.clear();
    }
}
