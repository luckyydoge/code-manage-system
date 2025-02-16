package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.login.model.entity.ChangePasswordEntity;
import edu.csu.codemanagesystem.domain.login.model.entity.UserEntity;
import edu.csu.codemanagesystem.domain.login.service.ILogin;
import edu.csu.codemanagesystem.type.Response;
import edu.csu.codemanagesystem.type.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class LoginController {

    @Autowired
    ILogin login;

    @PostMapping("/login")
    public Response<String> login(@RequestBody UserEntity user) {
        if (login.login(user)) {
            return Response.<String>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(user.getType())
                    .build();
        }
        return Response.<String>builder()
                .code(ResponseCode.USERID_OR_PASSWORD_INCORRECT.getCode())
                .info(ResponseCode.USERID_OR_PASSWORD_INCORRECT.getInfo())
                .build();

    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/changePassword")
    public Response<String> changePassword(@RequestBody ChangePasswordEntity changePasswordEntity) {
        Boolean status = login.changePassword(changePasswordEntity);
        if (status) {
            return Response.<String>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        }
        return Response.<String>builder()
                .code(ResponseCode.OLD_PASSWORD_INCORRECT.getCode())
                .info(ResponseCode.OLD_PASSWORD_INCORRECT.getInfo())
                .build();
    }
}
