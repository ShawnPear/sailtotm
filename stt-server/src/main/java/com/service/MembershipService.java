package com.service;

import com.dto.AdminTopUpDto;
import com.dto.MembershipDTO;
import com.dto.PasswordChangeDTO;
import com.vo.MembershipHistoryVO;
import com.vo.MembershipVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MembershipService {
    MembershipVO getByUserId(String userId);

    Boolean add(MembershipDTO dto);

    Boolean resetPassword(PasswordChangeDTO dto);

    @Transactional
    Boolean topUp(AdminTopUpDto dto);

    List<MembershipHistoryVO> getHistoryByUserId(String userId, String status, String page, String pageSize);
}
