package edu.csu.codemanagesystem.domain.admin.model.entity;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SemesterEntity {
    private Long semesterId;
    private String name;
    private String status;
    private Date startTime;
    private Date endTime;
}
