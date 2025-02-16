package edu.csu.codemanagesystem.domain.teacher.service;

import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;

public interface IClassManageService {
    Boolean createClass(ClassEntity classEntity);
}
