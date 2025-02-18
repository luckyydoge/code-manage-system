package edu.csu.codemanagesystem.infrastructure.po;

import lombok.Data;

import java.util.Date;

@Data
public class Job {
    private Long id;
    private Long jobId;
    private String title;
    private String content;
    private Long courseId;
    private Long classId;
    private Date startTime;
    private Date endTime;
    private String status;
}
