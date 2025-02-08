package edu.csu.codemanagesystem.infrastructure.po;

import lombok.Data;

import java.util.Date;

@Data
public class Semester {
    private Long id;
    private Long semesterId;
    private String name;
    private String status;
    private Date startTime;
    private Date endTime;
}
