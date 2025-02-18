package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.ClassPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IClassDao {
    int queryClassCount();

    void createClass(ClassPO classPo);

    String queryCourseNameByCourseId(Long courseId);
}
