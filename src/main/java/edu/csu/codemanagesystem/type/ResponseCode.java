package edu.csu.codemanagesystem.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS("000", "操作成功"),
    FAILURE("001", "操作失败,请联系工作人员"),
    OLD_PASSWORD_INCORRECT("002", "旧密码错误"),
    USERID_OR_PASSWORD_INCORRECT("003", "用户名或密码错误"),
    ;
    private String code;
    private String info;
}
