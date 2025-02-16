package edu.csu.codemanagesystem.domain.login.service;

import edu.csu.codemanagesystem.domain.login.model.entity.ChangePasswordEntity;
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
        log.info("{} try to login", userEntity.getUserId());
        UserEntity userEntityRes = loginRepository.queryUser(userEntity);
        if (null == userEntityRes) {
            log.info("{} login failed", userEntity.getUserId());
            return false;
        }
        userEntity.setType(userEntityRes.getType());
        log.info("{} login success", userEntity.getUserId());
        return true;
    }

    @Override
    public Boolean changePassword(ChangePasswordEntity changePasswordEntity) {
        log.info("{} try to change password", changePasswordEntity.getUserId());
        UserEntity userEntityReq = UserEntity.builder()
                .userId(changePasswordEntity.getUserId())
                .password(changePasswordEntity.getOldPassword())
                .build();
        UserEntity userEntityRes = loginRepository.queryUser(userEntityReq);
        if (null == userEntityRes) {
            log.info("{} change password failed", changePasswordEntity.getUserId());
            return false;
        }
        loginRepository.changePassword(changePasswordEntity);
        log.info("{} change password success", changePasswordEntity.getUserId());
        return true;
    }
}
