package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.StudentJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentJobDao {
    void insertStudentJobBatch(List<StudentJob> studentJobList);

    List<Long> queryJobIdByStudentId(Long studentId);

    List<StudentJob> queryStudentJobByFactor(StudentJob req);

    void updateStudentJob(StudentJob studentJob);
}
