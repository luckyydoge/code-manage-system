package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.Job;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IJobDao {
    void insertJob(Job job);

    int queryJobCount();

    List<Job> queryJobByFactor(Job jobReq);

    List<Job> queryJobByJobId(List<Long> jobIdList);

    void updateJobStatus(Job job);
}
