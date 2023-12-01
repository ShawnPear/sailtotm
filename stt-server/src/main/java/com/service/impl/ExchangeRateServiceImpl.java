package com.service.impl;

import com.dto.ExchangeRateDTO;
import com.entity.ExchangeRate;
import com.mapper.ExchangeRateMapper;
import com.service.ExchangeRateService;
import com.vo.ExchangeRateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    ExchangeRateMapper exchangeRateMapper;

    @Override
    public Boolean updateExchangeRate(ExchangeRateDTO dto) {
        ExchangeRate rate = ExchangeRate.builder()
                .updatedDate(LocalDateTime.now().toString())
                .rmb(Double.valueOf(dto.getRmb()))
                .usd(Double.valueOf(dto.getUsd()))
                .manat(Double.valueOf(dto.getManat()))
                .build();
        Boolean status = exchangeRateMapper.insert(rate);
        return status;
    }

    @Override
    public ExchangeRateVO getNewestExchangeRate() {
        ExchangeRate exchangeRate = exchangeRateMapper.selectNewest();
        ExchangeRateVO rateVO = ExchangeRateVO.builder().build();
        BeanUtils.copyProperties(exchangeRate, rateVO);
        return rateVO;
    }
}
