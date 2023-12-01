package com.service;

import com.dto.ExchangeRateDTO;
import com.vo.ExchangeRateVO;

public interface ExchangeRateService {
    public Boolean updateExchangeRate(ExchangeRateDTO rate);

    public ExchangeRateVO getNewestExchangeRate();
}
