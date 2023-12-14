package com.service;

import com.dto.Search.TaobaoSearchDTO;
import com.dto.Search.TaobaoSearchDetailDTO;
import com.vo.TaobaoGood.TaobaoGoodDetailVO;
import com.exception.user.OneBoundApiException;
import com.vo.TaobaoGood.TaobaoGoodDetailRawJsonVO;
import com.vo.TaobaoGood.TaobaoGoodListVO;
import org.springframework.transaction.annotation.Transactional;

public interface OneBoundApiService {
    public TaobaoGoodListVO taoBaoSearch(TaobaoSearchDTO dto) throws OneBoundApiException;

    public TaobaoGoodDetailRawJsonVO taoBaoSearchDetail(TaobaoSearchDetailDTO dto) throws OneBoundApiException;

    TaobaoGoodDetailVO parseToDetail(String detailJson);

    @Transactional
    TaobaoGoodDetailVO translatorDetail(TaobaoGoodDetailVO vo);
}
