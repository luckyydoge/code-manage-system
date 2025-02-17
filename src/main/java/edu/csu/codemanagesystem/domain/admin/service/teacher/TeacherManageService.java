package edu.csu.codemanagesystem.domain.admin.service.teacher;

import edu.csu.codemanagesystem.domain.admin.repository.IAdminRepository;
import edu.csu.codemanagesystem.domain.admin.service.ITeacherManageService;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeacherManageService implements ITeacherManageService {

    private final IAdminRepository repository;

    public TeacherManageService(IAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean addTeacher(TeacherEntity teacherEntity) {
        Boolean success = repository.createTeacher(teacherEntity);
        log.info("try to insert teacher : {}, status : {}", teacherEntity.getTeacherId(), success);
        return success;
    }

    @Override
    public Boolean deleteTeacher(TeacherEntity teacherEntity) {
        return null;
    }

    @Override
    public List<TeacherEntity> queryAllTeachers() {
        List<TeacherEntity> teacherEntityList = repository.queryAllTeachers();
        return teacherEntityList;
    }
}
