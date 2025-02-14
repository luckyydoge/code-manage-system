package edu.csu.codemanagesystem.domain.login.repository;

import edu.csu.codemanagesystem.domain.login.model.entity.ChangePasswordEntity;
import edu.csu.codemanagesystem.domain.login.model.entity.UserEntity;

public interface ILoginRepository {

    UserEntity  queryUser(UserEntity user);

    void changePassword(ChangePasswordEntity changePasswordEntity);
}

