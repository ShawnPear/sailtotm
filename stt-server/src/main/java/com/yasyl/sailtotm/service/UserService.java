package com.yasyl.sailtotm.service;


import com.yasyl.sailtotm.dto.UserAccount.UserLoginDTO;
import com.yasyl.sailtotm.dto.UserAccount.UserRegisterDTO;
import com.yasyl.sailtotm.dto.UserAccount.UserResetEmailDTO;
import com.yasyl.sailtotm.dto.UserAccount.UserResetPasswordDTO;
import com.yasyl.sailtotm.dto.UserAccount.UserResetUserDTO;
import com.yasyl.sailtotm.entity.User;
import com.yasyl.sailtotm.vo.UserVO;


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
