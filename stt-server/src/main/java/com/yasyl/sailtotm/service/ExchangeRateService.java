package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.ExchangeRateDTO;
import com.yasyl.sailtotm.vo.ExchangeRateVO;

public interface ExchangeRateService {
    public Boolean updateExchangeRate(ExchangeRateDTO rate);

    public ExchangeRateVO getNewestExchangeRate();
}
