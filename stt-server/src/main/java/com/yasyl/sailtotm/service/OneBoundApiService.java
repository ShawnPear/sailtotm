package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.Search.TaobaoSearchDTO;
import com.yasyl.sailtotm.dto.Search.TaobaoSearchDetailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yasyl.sailtotm.vo.TaobaoGood.TaobaoGoodDetailVO;
import com.yasyl.sailtotm.exception.user.OneBoundApiException;
import com.yasyl.sailtotm.vo.TaobaoGood.TaobaoGoodDetailRawJsonVO;
import com.yasyl.sailtotm.vo.TaobaoGood.TaobaoGoodListVO;
import org.springframework.transaction.annotation.Transactional;

public interface OneBoundApiService {
    public TaobaoGoodListVO taoBaoSearch(TaobaoSearchDTO dto) throws OneBoundApiException;

    public TaobaoGoodDetailRawJsonVO taoBaoSearchDetail(TaobaoSearchDetailDTO dto) throws OneBoundApiException;

    TaobaoGoodDetailVO parseToDetail(String detailJson) throws JsonProcessingException;

    @Transactional
    TaobaoGoodDetailVO translatorDetail(TaobaoGoodDetailVO vo);
}
