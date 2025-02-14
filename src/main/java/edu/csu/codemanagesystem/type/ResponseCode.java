package edu.csu.codemanagesystem.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS("000", "操作成功"),
    OLD_PASSWORD_INCORRECT("001", "旧密码错误")
    ;
    private String code;
    private String info;
}
