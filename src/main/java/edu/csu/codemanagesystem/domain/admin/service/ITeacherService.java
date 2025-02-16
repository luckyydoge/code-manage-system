package edu.csu.codemanagesystem.domain.admin.service;

import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.infrastructure.po.Teacher;

public interface ITeacherService {
    Boolean addTeacher(TeacherEntity teacherEntity);

    Boolean deleteTeacher(TeacherEntity teacherEntity);
}
