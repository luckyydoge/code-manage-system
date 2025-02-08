package edu.csu.codemanagesystem.domain.login.repository;

import edu.csu.codemanagesystem.domain.login.model.entity.UserEntity;

public interface ILoginRepository {

    Boolean loginCheck(UserEntity user);
}
