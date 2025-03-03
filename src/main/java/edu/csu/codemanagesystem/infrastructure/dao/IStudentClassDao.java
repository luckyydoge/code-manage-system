package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.Student;
import edu.csu.codemanagesystem.infrastructure.po.StudentClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentClassDao {
    void insertBatch(List<StudentClass> studentClassList);

    List<Long> queryStudentIdByClassId(Long classId);

    List<StudentClass> queryByFactor(StudentClass studentClassFactor);
}
