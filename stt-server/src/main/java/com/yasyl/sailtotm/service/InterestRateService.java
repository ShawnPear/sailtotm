package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.InterestRateDTO;
import com.yasyl.sailtotm.vo.InterestRateVO;

public interface InterestRateService {
    public Boolean updateInterestRate(InterestRateDTO rate);

    public InterestRateVO getNewestInterestRate();
}
