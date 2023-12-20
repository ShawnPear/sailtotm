package com.service;


import com.dto.UserAccount.*;
import com.entity.User;
import com.vo.UserVO;


public interface UserService {
    User login(UserLoginDTO userLoginDTO);

    Boolean register(UserRegisterDTO userRegisterDTO);

    Boolean resetPassword(UserResetPasswordDTO dto);

    Boolean resetEmail(UserResetEmailDTO dto);

    Boolean updateUserInfo(UserResetUserDTO dto);

    String generateEmailActivateKey(String email);

    Boolean verifyEmailActivateKey(String token, String email);

    Boolean activate(String email);

    UserVO getByUserId(String userId);
}
