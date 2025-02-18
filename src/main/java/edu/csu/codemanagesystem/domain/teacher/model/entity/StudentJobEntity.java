package edu.csu.codemanagesystem.domain.teacher.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentJobEntity {
    private Long jobId;
    private Long studentId;
    private String status;
}
