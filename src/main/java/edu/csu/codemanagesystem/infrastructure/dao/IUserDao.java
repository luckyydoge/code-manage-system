package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserDao {
    User queryUser(User userReq);

    void updateUserByUserId(User userReq);

    void insertUserBatch(List<User> userList);
}
