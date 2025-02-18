package edu.csu.codemanagesystem.domain.teacher.service;

import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.StudentJobEntity;

import java.util.List;

public interface IJobService {
    Boolean createJob(JobEntity jobEntity);

    List<JobEntity> queryJobByFactor(JobEntity jobEntity);

    List<JobEntity> queryJobByStudentId(Long studentId);

    List<JobEntity> queryJobByStudentJobFactor(StudentJobEntity factor);
}
