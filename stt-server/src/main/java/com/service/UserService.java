package com.service;


import com.dto.UserLoginDTO;
import com.entity.User;

import javax.security.auth.login.AccountNotFoundException;


public interface UserService {
    User login(UserLoginDTO userLoginDTO);
}
