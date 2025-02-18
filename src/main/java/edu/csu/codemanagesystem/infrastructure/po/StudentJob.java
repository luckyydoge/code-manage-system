package edu.csu.codemanagesystem.infrastructure.po;

import lombok.Data;

@Data
public class StudentJob {
    private Long id;
    private Long jobId;
    private Long studentId;
    private String status;
}
