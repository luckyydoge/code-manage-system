package edu.csu.codemanagesystem.domain.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemesterEntity {
    private Long semesterId;
    private String name;
    private String status;
    private Date startTime;
    private Date endTime;
}
