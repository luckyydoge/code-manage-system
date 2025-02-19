package edu.csu.codemanagesystem.domain.teacher.service;

import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;

import java.util.List;

public interface IClassManageService {
    Boolean createClass(ClassEntity classEntity);

    List<ClassEntity> queryClassByFactor(ClassEntity classEntityFactor);

    List<ClassEntity> queryClassByStudentId(Long studentId);
}
