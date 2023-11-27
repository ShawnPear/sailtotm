package com.service;

import com.dto.TaobaoSearchDTO;
import com.entity.TaobaoGood.TaobaoGoodList;
import com.exception.user.OneBoundApiException;

public interface OneBoundApiService {
    public TaobaoGoodList taoBaoSearch(TaobaoSearchDTO dto) throws OneBoundApiException;
}
