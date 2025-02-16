package edu.csu.codemanagesystem.domain.teacher.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassEntity {
    private Long classId;
    private String name;
    private Integer peopleCount;
    private Long teacherId;
    private Long semesterId;
    private Long courseId;
}
