package edu.csu.codemanagesystem.domain.admin.service.teacher;

import edu.csu.codemanagesystem.domain.admin.repository.IAdminRepository;
import edu.csu.codemanagesystem.domain.admin.service.ITeacherService;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.infrastructure.po.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeacherService implements ITeacherService {

    private final IAdminRepository repository;

    public TeacherService(IAdminRepository repository) {
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
}
