package com.service;

import com.dto.TaobaoSearchDTO;
import com.dto.TaobaoSearchDetailDTO;
import com.entity.TaobaoGoodList.TaobaoGoodList;
import com.exception.user.OneBoundApiException;
import com.vo.TaobaoGoodDetailVO;
import com.vo.TaobaoGoodListVO;

public interface OneBoundApiService {
    public TaobaoGoodListVO taoBaoSearch(TaobaoSearchDTO dto) throws OneBoundApiException;

    public TaobaoGoodDetailVO taoBaoSearchDetail(TaobaoSearchDetailDTO dto) throws OneBoundApiException;
}
