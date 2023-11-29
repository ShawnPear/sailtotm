package com.service;


import com.dto.UserAccount.*;
import com.entity.User;


public interface UserService {
    User login(UserLoginDTO userLoginDTO);

    Boolean register(UserRegisterDTO userRegisterDTO);

    Boolean resetPassword(UserResetPasswordDTO dto);

    Boolean resetEmail(UserResetEmailDTO dto);

    Boolean updateUserInfo(UserResetUserDTO dto);
}
