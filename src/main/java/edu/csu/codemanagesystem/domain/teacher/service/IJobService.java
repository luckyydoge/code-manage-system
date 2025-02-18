package edu.csu.codemanagesystem.domain.teacher.service;

import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;

public interface IJobService {
    Boolean createJob(JobEntity jobEntity);
}
