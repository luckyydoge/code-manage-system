package edu.csu.codemanagesystem.domain.login.model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private Long userId;
    private String password;
    private String type;
}
