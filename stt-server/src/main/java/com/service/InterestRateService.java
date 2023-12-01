package com.service;

import com.dto.InterestRateDTO;
import com.vo.InterestRateVO;

public interface InterestRateService {
    public Boolean updateInterestRate(InterestRateDTO rate);

    public InterestRateVO getNewestInterestRate();
}
