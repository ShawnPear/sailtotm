package com.service.impl;

import com.constant.JwtClaimsConstant;
import com.constant.MessageConstant;
import com.dto.UserAccount.*;
import com.entity.User;
import com.exception.user.AccountExistException;
import com.exception.user.AccountNotFoundException;
import com.exception.user.EmailFormatErrorException;
import com.exception.user.PasswordErrorException;
import com.mapper.UserMapper;
import com.properties.JwtProperties;
import com.service.UserService;
import com.utils.EmailUtil;
import com.utils.JwtUtil;
import com.vo.UserVO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.utils.PasswordUtil.getMD5Password;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtProperties jwtProperties;

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        User user = userMapper.getByEmail(userLoginDTO.getEmail());

        if (user == null) {
            throw new AccountNotFoundException(MessageConstant.USER_LOGIN_ERROR_NOT_EXIST);
        }

        String password = userLoginDTO.getPassword();

        String md5password = getMD5Password(password);
        if (!md5password.equals(user.getPassword())) {
            throw new PasswordErrorException(MessageConstant.USER_PASSWORD_ERROR);
        }

        return user;
    }

    @Override
    public Boolean register(UserRegisterDTO userRegisterDTO) {
        String email = userRegisterDTO.getEmail();
        if (!EmailUtil.isValidEmail(email)) {
            throw new EmailFormatErrorException(MessageConstant.USER_REGISTER_EMAIL_FORMAT_ERROR);
        }
        if (userMapper.getByEmail(userRegisterDTO.getEmail()) != null) {
            throw new AccountExistException(MessageConstant.USER_REGISTER_ERROR_EMAIL_EXIST);
        }
        String password = userRegisterDTO.getPassword();
        String md5password = getMD5Password(password);
        String name = userRegisterDTO.getLastName() + " " + userRegisterDTO.getFirstName();
        LocalDateTime createdDate = LocalDateTime.now();
        User user = User.builder()
                .email(userRegisterDTO.getEmail())
                .firstName(userRegisterDTO.getFirstName())
                .lastName(userRegisterDTO.getLastName())
                .password(md5password)
                .userName(name)
                .createdDate(createdDate)
                .updatedDate(createdDate)
                .build();
        Boolean status = userMapper.insert(user);
        return status;
    }

    @Override
    public Boolean resetPassword(UserResetPasswordDTO dto) {
        User user = userMapper.getByUserId(Long.valueOf(dto.getUserId()));
        if (user == null) {
            throw new AccountNotFoundException(MessageConstant.USER_NOT_EXIST);
        }
        String md5OldPassword = getMD5Password(dto.getOldPassword());
        if (!user.getPassword().equals(md5OldPassword)) {
            throw new PasswordErrorException(MessageConstant.USER_RESET_PASSWORD_ERROR);
        }
        String md5NewPassword = getMD5Password(dto.getNewPassword());
        User user1 = User.builder()
                .userId(Integer.parseInt(dto.getUserId()))
                .password(md5NewPassword)
                .updatedDate(LocalDateTime.now())
                .build();
        Boolean status = userMapper.updateByUserId(user1);
        return status;
    }

    @Override
    public Boolean resetEmail(UserResetEmailDTO dto) {
        Long id = Long.valueOf(dto.getUserId());
        String email = dto.getNewEmail();
        if (!EmailUtil.isValidEmail(email)) {
            throw new EmailFormatErrorException(MessageConstant.USER_REGISTER_EMAIL_FORMAT_ERROR);
        }
        User user = userMapper.getByUserId(id);
        if (user == null) {
            throw new AccountNotFoundException(MessageConstant.USER_NOT_EXIST);
        }
        User user1 = User.builder()
                .userId(id)
                .email(dto.getNewEmail())
                .updatedDate(LocalDateTime.now())
                .enable(false)
                .build();
        Boolean status = userMapper.updateByUserId(user1);
        return status;
    }

    @Override
    public Boolean updateUserInfo(UserResetUserDTO dto) {
        Long id = Long.valueOf(dto.getUserId());
        User user = userMapper.getByUserId(id);
        if (user == null) {
            throw new AccountNotFoundException(MessageConstant.USER_NOT_EXIST);
        }
        User user1 = User.builder()
                .userId(Long.parseLong(dto.getUserId()))
                .updatedDate(LocalDateTime.now())
                .build();
        BeanUtils.copyProperties(dto, user1);
        Boolean status = userMapper.updateByUserId(user1);
        return status;
    }

    @Override
    public String generateEmailActivateKey(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMAIL, email);
        String token = JwtUtil.createJWT(jwtProperties.getEmailSecretKey(), jwtProperties.getEmailTtl(), claims);
        return token;
    }

    @Override
    public Boolean verifyEmailActivateKey(String token, String email) {
        Claims claims = JwtUtil.parseJWT(jwtProperties.getEmailSecretKey(), token);
        if (claims == null) {
            return false;
        }
        String email2 = claims.get(JwtClaimsConstant.EMAIL).toString();
        return email2.equals(email);
    }

    @Override
    public Boolean activate(String email) {
        return userMapper.updateActivateStatusByEmail(email, 1);
    }

    @Override
    public UserVO getByUserId(String userId) {
        User user = userMapper.getByUserId(Long.valueOf(userId));
        if (user == null) {
            throw new AccountNotFoundException(MessageConstant.USER_NOT_EXIST);
        }
        UserVO vo = UserVO.builder().build();
        BeanUtils.copyProperties(user, vo);

        return vo;
    }
}
