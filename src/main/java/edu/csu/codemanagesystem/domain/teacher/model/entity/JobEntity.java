package edu.csu.codemanagesystem.domain.teacher.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobEntity {
    private Long jobId;
    private String title;
    private String content;
    private String status;
    private Long courseId;
    private Long classId;
    private Date startTime;
    private Date endTime;
}
