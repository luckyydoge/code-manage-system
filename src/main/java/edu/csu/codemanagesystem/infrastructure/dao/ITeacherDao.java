package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITeacherDao {
    int queryTeacherCount();

    void insertTeacherBatch(List<Teacher> teacherList);

    List<Teacher> queryAllTeachers();

    Teacher queryTeacherById(Long id);
}
