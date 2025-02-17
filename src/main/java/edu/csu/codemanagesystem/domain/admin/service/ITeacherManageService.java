package edu.csu.codemanagesystem.domain.admin.service;

import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;

import java.util.List;

public interface ITeacherManageService {
    Boolean addTeacher(TeacherEntity teacherEntity);

    Boolean deleteTeacher(TeacherEntity teacherEntity);

    List<TeacherEntity> queryAllTeachers();
}
