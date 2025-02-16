package edu.csu.codemanagesystem.domain.teacher.repository;

import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;

public interface ITeacherRepository {
    void createClass(ClassEntity classEntity);
}
