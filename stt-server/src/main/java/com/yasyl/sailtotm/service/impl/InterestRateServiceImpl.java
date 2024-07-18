package com.yasyl.sailtotm.service.impl;

import com.yasyl.sailtotm.dto.InterestRateDTO;
import com.yasyl.sailtotm.entity.InterestRate;
import com.yasyl.sailtotm.mapper.InterestRateMapper;
import com.yasyl.sailtotm.service.InterestRateService;
import com.yasyl.sailtotm.vo.InterestRateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestRateServiceImpl implements InterestRateService {
    @Autowired
    InterestRateMapper interestRateMapper;

    @Override
    public Boolean updateInterestRate(InterestRateDTO dto) {
        InterestRate rate = InterestRate.builder()
                .interestRate(Double.valueOf(dto.getInterestRate()))
                .build();
        Boolean status = interestRateMapper.update(rate);
        return status;
    }

    @Override
    public InterestRateVO getNewestInterestRate() {
        InterestRate interestRate = interestRateMapper.selectNewest();
        InterestRateVO rateVO = InterestRateVO.builder().build();
        BeanUtils.copyProperties(interestRate, rateVO);
        return rateVO;
    }
}
