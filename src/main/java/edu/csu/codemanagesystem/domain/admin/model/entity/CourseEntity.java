package edu.csu.codemanagesystem.domain.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
    private Long courseId;
    private String name;
    private String status;
    private Date startTime;
    private Date endTime;
    private Long semesterId;
}
