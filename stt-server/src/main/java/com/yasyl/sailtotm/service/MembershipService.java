package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.AdminTopUpDto;
import com.yasyl.sailtotm.dto.MembershipDTO;
import com.yasyl.sailtotm.dto.MembershipPayDTO;
import com.yasyl.sailtotm.dto.PasswordChangeDTO;
import com.yasyl.sailtotm.vo.MembershipHistoryVO;
import com.yasyl.sailtotm.vo.MembershipVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MembershipService {
    MembershipVO getByUserId(String userId);

    Boolean add(MembershipDTO dto);

    Boolean resetPassword(PasswordChangeDTO dto);

    @Transactional
    Boolean topUp(AdminTopUpDto dto);

    List<MembershipHistoryVO> getHistoryByUserId(String userId, String status, String page, String pageSize);

    @Transactional
    Boolean pay(MembershipPayDTO build);
}
