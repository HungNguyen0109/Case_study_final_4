package com.codegym.service.user;

import com.codegym.model.entity.User;
import com.codegym.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUserName(String username);
    boolean existsByUsername(String username);

}
