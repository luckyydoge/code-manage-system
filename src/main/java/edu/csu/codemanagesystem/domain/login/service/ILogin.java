package edu.csu.codemanagesystem.domain.login.service;

import edu.csu.codemanagesystem.domain.login.model.entity.UserEntity;

public interface ILogin {
    Boolean login(UserEntity userEntity);
}
