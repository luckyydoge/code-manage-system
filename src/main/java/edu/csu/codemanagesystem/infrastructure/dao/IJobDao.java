package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.Job;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IJobDao {
    void insertJob(Job job);

    int queryJobCount();
}
