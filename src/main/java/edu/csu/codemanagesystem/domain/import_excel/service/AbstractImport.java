package edu.csu.codemanagesystem.domain.import_excel.service;

import cn.idev.excel.FastExcel;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.import_excel.repository.IImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public abstract class AbstractImport<T> implements IImportExcel<T>{

    protected final IImportRepository importRepository;

    public AbstractImport(IImportRepository importRepository) {
        this.importRepository = importRepository;
    }

    @Override
    public Boolean ImportExcel(InputStream inputStream) {
        int batchCount = 50;
        List<T> cacheList = new ArrayList<>(55);
        log.info("input stream {}", inputStream.toString());
        FastExcel.read(inputStream, getClazz(), new ReadListener<T>() {
            int count = getCount();

            @Override
            public void invoke(T entity, AnalysisContext analysisContext) {
                setId(entity, count);
                log.info("analyze entity {}", entity.toString());
                count++;
                cacheList.add(entity);
                if (cacheList.size() >= batchCount) {
                    saveData(cacheList);
                }

            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                saveData(cacheList);
            }
        }).sheet().doRead();
        log.info("import success");

        return true;
    }

    protected abstract int getCount();

    protected abstract void setId(T entity, int count);

    protected abstract Class<T> getClazz();

    protected abstract void saveData(List<T> cacheList);

}
