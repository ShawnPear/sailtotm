package com.service.impl;

import com.constant.MessageConstant;
import com.dto.UserLoginDTO;
import com.entity.User;
import com.exception.AccountNotFoundException;
import com.exception.PasswordErrorException;
import com.mapper.UserMapper;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        User user = userMapper.getByEmail(userLoginDTO.getEmail());

        if (user == null) {
            throw new AccountNotFoundException(MessageConstant.USER_LOGIN_ERROR_NOT_EXIST);
        }

        String password = userLoginDTO.getPassword();

        String md5password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!md5password.equals(user.getPassword())) {
            throw new PasswordErrorException(MessageConstant.USER_PASSWORD_ERROR);
        }

        return user;
    }
}
