package edu.csu.codemanagesystem.infrastructure.po;

import lombok.Data;

@Data
public class Teacher {
    private Long id;
    private Long teacherId;
    private String name;
    private String email;
}
