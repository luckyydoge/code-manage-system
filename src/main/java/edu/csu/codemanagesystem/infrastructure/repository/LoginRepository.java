package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.login.model.entity.ChangePasswordEntity;
import edu.csu.codemanagesystem.domain.login.model.entity.UserEntity;
import edu.csu.codemanagesystem.domain.login.repository.ILoginRepository;
import edu.csu.codemanagesystem.infrastructure.dao.IUserDao;
import edu.csu.codemanagesystem.infrastructure.po.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository implements ILoginRepository {

    @Resource
    IUserDao userDao;

    @Override
    public UserEntity queryUser(UserEntity userEntity) {
        User userReq = new User();
        userReq.setUserId(userEntity.getUserId());
        userReq.setPassword(userEntity.getPassword());
        User userRes = userDao.queryUser(userReq);
        if (null == userRes) {
            return null;
        }
        return UserEntity.builder()
                .userId(userRes.getUserId())
                .password(userRes.getPassword())
                .type(userRes.getType())
                .build();
    }

    @Override
    public void changePassword(ChangePasswordEntity changePasswordEntity) {
        User userReq = new User();
        userReq.setUserId(changePasswordEntity.getUserId());
        userReq.setPassword(changePasswordEntity.getNewPassword());
        userDao.updateUserByUserId(userReq);
    }
}
