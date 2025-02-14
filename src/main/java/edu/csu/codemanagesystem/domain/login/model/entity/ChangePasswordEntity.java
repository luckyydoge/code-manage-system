package edu.csu.codemanagesystem.domain.login.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordEntity {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
