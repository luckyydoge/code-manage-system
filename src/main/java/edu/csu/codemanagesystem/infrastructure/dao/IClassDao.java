package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.ClassPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IClassDao {
    int queryClassCount();

    void createClass(ClassPO classPo);

    String queryCourseNameByCourseId(Long courseId);

    List<ClassPO> queryClassByFactor(ClassPO factor);

    List<ClassPO> queryClassByClassIdList(List<Long> classIdList);
}
