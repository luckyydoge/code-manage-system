package edu.csu.codemanagesystem.infrastructure.po;

import lombok.Data;

import java.util.Date;

@Data
public class Course {
    private Long id;
    private String name;
    private Long courseId;
    private String status;
    private Date startTime;
    private Date endTime;
}
