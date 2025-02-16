package edu.csu.codemanagesystem.infrastructure.po;

import lombok.Data;

@Data
public class ClassPO {
    private Long id;
    private Long classId;
    private String name;
    private Integer peopleCount;
    private Long semesterId;
    private Long courseId;
    private Long teacherId;
}
