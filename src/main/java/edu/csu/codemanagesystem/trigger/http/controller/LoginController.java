package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.login.model.entity.UserEntity;
import edu.csu.codemanagesystem.domain.login.service.ILogin;
import edu.csu.codemanagesystem.type.Response;
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
                    .code("000")
                    .info("")
                    .build();
        }
        return Response.<String>builder()
                .code("001")
                .info("")
                .build();

    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
