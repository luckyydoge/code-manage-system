package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.StudentClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentClassDao {
    void insertBatch(List<StudentClass> studentClassList);
}
