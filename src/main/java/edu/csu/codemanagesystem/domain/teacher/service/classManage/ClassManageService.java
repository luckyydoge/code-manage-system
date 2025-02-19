package edu.csu.codemanagesystem.domain.teacher.service.classManage;

import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;
import edu.csu.codemanagesystem.domain.teacher.repository.ITeacherRepository;
import edu.csu.codemanagesystem.domain.teacher.service.IClassManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClassManageService implements IClassManageService {

    private final ITeacherRepository teacherRepository;

    public ClassManageService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Boolean createClass(ClassEntity classEntity) {
        teacherRepository.createClass(classEntity);
        return true;
    }

    @Override
    public List<ClassEntity> queryClassByFactor(ClassEntity classEntityFactor) {
        List<ClassEntity> classEntitiesList = teacherRepository.queryClassByFactor(classEntityFactor);
        log.info("queryClassByFactor factor:{}, classEntitiesList:{}", classEntityFactor, classEntitiesList);
        return classEntitiesList;
    }

    @Override
    public List<ClassEntity> queryClassByStudentId(Long studentId) {
        List<ClassEntity> classEntitiesList = teacherRepository.queryClassBuStudentId(studentId);
        return classEntitiesList;
    }
}
