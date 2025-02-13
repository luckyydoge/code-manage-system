package edu.csu.codemanagesystem.infrastructure.repository;

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
    public Boolean loginCheck(UserEntity userEntity) {
        User userReq = new User();
        userReq.setUserId(userEntity.getUserId());
        userReq.setPassword(userEntity.getPassword());
        User userRes = userDao.queryUser(userReq);
        if (null == userRes) {
            return false;
        }
        userEntity.setType(userRes.getType());
        return true;
    }
}
