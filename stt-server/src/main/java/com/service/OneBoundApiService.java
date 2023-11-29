package com.service;

import com.dto.TaobaoSearchDTO;
import com.dto.TaobaoSearchDetailDTO;
import com.exception.user.OneBoundApiException;
import com.vo.TaobaoGood.TaobaoGoodDetailVO;
import com.vo.TaobaoGood.TaobaoGoodListVO;

public interface OneBoundApiService {
    public TaobaoGoodListVO taoBaoSearch(TaobaoSearchDTO dto) throws OneBoundApiException;

    public TaobaoGoodDetailVO taoBaoSearchDetail(TaobaoSearchDetailDTO dto) throws OneBoundApiException;
}
