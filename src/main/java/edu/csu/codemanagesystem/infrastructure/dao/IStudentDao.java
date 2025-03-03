package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentDao {
    int queryStudentCount();

    void insertStudentBatch(List<Student> studentList);

    List<Student> queryStudentByStudentIdList(List<Long> studentIdList);

    List<Student> queryStudentByFactor(Student req);
}
