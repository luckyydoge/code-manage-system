package edu.csu.codemanagesystem.domain.login.model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserEntity {
    private Long userId;
    private String password;
}
