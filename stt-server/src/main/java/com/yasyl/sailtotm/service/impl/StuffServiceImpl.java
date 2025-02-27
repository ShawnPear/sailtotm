package com.yasyl.sailtotm.service.impl;

import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.Stuff.StuffLoginDTO;
import com.yasyl.sailtotm.dto.Stuff.StuffRegisterDTO;
import com.yasyl.sailtotm.dto.Stuff.StuffStatusChangeDTO;
import com.yasyl.sailtotm.entity.Stuff;
import com.yasyl.sailtotm.entity.StuffStatusChange;
import com.yasyl.sailtotm.exception.user.AccountExistException;
import com.yasyl.sailtotm.exception.user.AccountNotFoundException;
import com.yasyl.sailtotm.exception.user.EmailFormatErrorException;
import com.yasyl.sailtotm.exception.user.PasswordErrorException;
import com.yasyl.sailtotm.mapper.StuffMapper;
import com.yasyl.sailtotm.service.StuffService;
import com.yasyl.sailtotm.utils.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.yasyl.sailtotm.utils.PasswordUtil.getMD5Password;

@Service
@Slf4j
public class StuffServiceImpl implements StuffService {

    @Autowired
    StuffMapper stuffMapper;

    @Override
    public Stuff login(StuffLoginDTO stuffLoginDTO) {
        Stuff stuff = stuffMapper.getByEmail(stuffLoginDTO.getEmail());

        if (stuff == null) {
            throw new AccountNotFoundException(MessageConstant.STUFF_LOGIN_ERROR_NOT_EXIST);
        }

        String password = stuffLoginDTO.getPassword();

        String md5password = getMD5Password(password);
        if (!md5password.equals(stuff.getPassword())) {
            throw new PasswordErrorException(MessageConstant.STUFF_PASSWORD_ERROR);
        }

        return stuff;
    }

    @Override
    public Boolean register(StuffRegisterDTO dto) {
        String email = dto.getEmail();
        if (!EmailUtil.isValidEmail(email)) {
            throw new EmailFormatErrorException(MessageConstant.STUFF_REGISTER_EMAIL_FORMAT_ERROR);
        }
        if (stuffMapper.getByEmail(dto.getEmail()) != null) {
            throw new AccountExistException(MessageConstant.STUFF_REGISTER_ERROR_EMAIL_EXIST);
        }
        String password = dto.getPassword();
        String md5password = getMD5Password(password);
        String name = dto.getLastName() + " " + dto.getFirstName();
        Stuff stuff = Stuff.builder()
                .createdDate(LocalDateTime.now())
                .email(dto.getEmail())
                .locationId(Long.parseLong(dto.getLocationId()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(md5password)
                .phone(dto.getPhoneNumber())
                .roleId(Long.parseLong(dto.getRoleId()))
                .salary(Double.parseDouble(dto.getSalary()))
                .stuffId(1)
                .statusId(4)
                .updatedDate(LocalDateTime.now())
                .build();
        Boolean status = stuffMapper.insert(stuff);
        return status;
    }

    @Override
    public Boolean changeStuffStatus(StuffStatusChangeDTO dto) {
        Stuff stuff = stuffMapper.getById(dto.getStuffId());
        if (stuff == null) {
            throw new AccountNotFoundException(MessageConstant.STUFF_ID_ERROR_NOT_EXIST);
        }
        StuffStatusChange change = StuffStatusChange.builder()
                .stuffId(Integer.parseInt(dto.getStuffId()))
                .statusId(dto.getStatusId())
                .locationId(dto.getLocationId())
                .roleId(dto.getRoleId())
                .build();
        return stuffMapper.updateStatusByStuffId(change);
    }
}
