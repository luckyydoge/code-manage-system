package edu.csu.codemanagesystem.domain.login.service;

import edu.csu.codemanagesystem.domain.login.model.entity.UserEntity;
import edu.csu.codemanagesystem.domain.login.repository.ILoginRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService implements ILogin{
    @Resource
    private final ILoginRepository loginRepository;

    public LoginService(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Boolean login(UserEntity userEntity) {
        Boolean success = loginRepository.loginCheck(userEntity);
        log.info("{} try to login, {}", userEntity.getUserId(), success);
        return success;
    }
}
