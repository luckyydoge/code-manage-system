package edu.csu.codemanagesystem.domain.import_excel.service.impl;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.import_excel.service.AbstractImport;
import edu.csu.codemanagesystem.domain.import_excel.service.IImportExcel;
import edu.csu.codemanagesystem.infrastructure.repository.ImportRepository;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component("student_import_service")
public class ImportStudentInfo extends AbstractImport<StudentEntity> {
    public ImportStudentInfo(ImportRepository importRepository) {
        super(importRepository);
    }

    private long baseCount = 300000;

    private Long classId;
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
    protected int getCount() {
        return importRepository.queryStudentCount();
    }

    @Override
    protected void setId(StudentEntity entity, int count) {
        entity.setStudentId(count + baseCount);
    }

    @Override
    protected Class<StudentEntity> getClazz() {
        return StudentEntity.class;
    }

    @Override
    protected void saveData(List<StudentEntity> cacheList) {
        importRepository.insertStudentBatch(cacheList);
        importRepository.insertStudentBatchToUsers(cacheList);
        importRepository.insertStudentBatchToStudentClass(cacheList, classId);
        cacheList.clear();

    }

}
