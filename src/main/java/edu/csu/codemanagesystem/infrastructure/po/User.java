package edu.csu.codemanagesystem.infrastructure.po;

import lombok.Data;

@Data
public class User {
    private Long id;
    private Long userId;
    private String password;
    private String type;
}
