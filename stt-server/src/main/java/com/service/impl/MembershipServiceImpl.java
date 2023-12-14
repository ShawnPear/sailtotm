package com.service.impl;

import com.dto.AdminTopUpDto;
import com.dto.MembershipDTO;
import com.dto.PasswordChangeDTO;
import com.entity.Membership;
import com.entity.MembershipHistory;
import com.enumeration.MembershipBalanceHistoryType;
import com.exception.user.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.MembershipMapper;
import com.service.MembershipService;
import com.utils.PasswordUtil;
import com.vo.MembershipHistoryVO;
import com.vo.MembershipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.constant.MessageConstant.*;

@Service
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    MembershipMapper mapper;

    @Override
    public MembershipVO getByUserId(String userId) {
        Membership membership = mapper.selectByUserId(userId);
        if (membership == null) throw new AccountNotFoundException(NO_REGISTER_MAMBERSHIP);
        MembershipVO vo = MembershipVO.builder().balance(String.valueOf(membership.getBalance())).membershipId(String.valueOf(membership.getMembershipId())).supportDate(String.valueOf(ChronoUnit.DAYS.between(membership.getCreatedDate(), LocalDateTime.now()) + 1)).build();
        return vo;
    }

    @Override
    public Boolean add(MembershipDTO dto) {
        try {
            Membership membership = Membership.builder().balance(0.0).password(PasswordUtil.getMD5Password(dto.getPassword())).userId(Integer.valueOf(dto.getUserId())).createdDate(LocalDateTime.now()).build();
            Membership membership1 = mapper.selectByUserId(dto.getUserId());
            if (membership1 == null)
                return mapper.add(membership);
            else
                throw new AccountExistException(ID_EXIST_ERROR);
        } catch (AccountExistException e) {
            throw new AccountExistException(ID_EXIST_ERROR);
        } catch (Exception e) {
            throw new ParamMissingException(PARAM_MISSING_ERROR);
        }
    }

    @Override
    public Boolean resetPassword(PasswordChangeDTO dto) {
        Membership membership = mapper.selectByUserId(dto.getUserId());
        String oldPass = PasswordUtil.getMD5Password(dto.getOldPassword());
        String newPass = PasswordUtil.getMD5Password(dto.getNewPassword());
        if (membership == null) {
            throw new IdNotExistException(ID_NOT_EXIST_ERROR);
        }
        if (!membership.getPassword().equals(oldPass)) {
            throw new PasswordErrorException(PAY_PASSWORD_ERROR);
        }
        return mapper.updatePassword(membership.getUserId(), newPass);
    }

    @Override
    public Boolean topUp(AdminTopUpDto dto) {
        MembershipHistory history = MembershipHistory.builder().membershipId(Integer.valueOf(dto.getMembershipId())).userId(Integer.valueOf(dto.getUserId())).stuffId(Integer.valueOf(dto.getStuffId())).statusId(Integer.valueOf(dto.getStatus())).createdDate(LocalDateTime.now()).build();
        if (history.getStatusId() == MembershipBalanceHistoryType.CHARGE) {
            history.setChange(Double.valueOf(dto.getChange()));
        } else if (history.getStatusId() == MembershipBalanceHistoryType.COST) {
            history.setChange(-Double.parseDouble(dto.getChange()));
        }
        Boolean status = mapper.topUpMoney(history) && mapper.topUpMoneyAddHistory(history);
        return status;
    }

    @Override
    public List<MembershipHistoryVO> getHistoryByUserId(String userId, String status, String page, String pageSize) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        Page<MembershipHistory> membershipHistories;
        if (status == null || status.isEmpty()) {
            membershipHistories = mapper.selectHistoryByUserId(userId);
        } else {
            membershipHistories = mapper.selectHistoryByUserIdAndStatus(userId, status);
        }
        List<MembershipHistoryVO> list = new ArrayList<>();
        for (MembershipHistory membershipHistory : membershipHistories.getResult()) {
            list.add(MembershipHistoryVO.builder().change(String.valueOf(membershipHistory.getChange())).statusId(String.valueOf(membershipHistory.getStatusId())).createdDate(String.valueOf(membershipHistory.getCreatedDate())).build());
        }
        return list;
    }
}
