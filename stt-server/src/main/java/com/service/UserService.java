package com.service;


import com.dto.*;
import com.entity.User;

import javax.security.auth.login.AccountNotFoundException;


public interface UserService {
    User login(UserLoginDTO userLoginDTO);

    Boolean register(UserRegisterDTO userRegisterDTO);

    Boolean resetPassword(UserResetPasswordDTO dto);

    Boolean resetEmail(UserResetEmailDTO dto);

    Boolean updateUserInfo(UserResetUserDTO dto);
}
