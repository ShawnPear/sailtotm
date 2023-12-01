package com.service.impl;

import com.dto.InterestRateDTO;
import com.entity.InterestRate;
import com.mapper.InterestRateMapper;
import com.service.InterestRateService;
import com.vo.InterestRateVO;
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
