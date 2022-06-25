package com.codegym.service.userInfo;

import com.codegym.model.entity.UserInfo;
import com.codegym.service.IGeneralService;

public interface IUserInfoService extends IGeneralService<UserInfo> {
    UserInfo findByUserId(Long id);
    Long findUserByUserInfo(Long id);
    boolean existsByEmail(String email);
}
