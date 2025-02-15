package edu.csu.codemanagesystem.infrastructure.po;

import lombok.Data;

@Data
public class Student {
    private Long id;
    private Long studentId;
    private String name;
    private String sex;
    private String phoneNumber;
    private String email;
    private String administrativeClass;
}
