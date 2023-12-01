package com.service;

import com.dto.ChinaDeliverDetailDTO;
import com.vo.ChinaDeliverDetailVO;

import java.util.List;

public interface ChinaDeliverDetailService {
    public List<ChinaDeliverDetailVO> getAll();

    public Boolean add(ChinaDeliverDetailDTO dto);

    public Boolean update(ChinaDeliverDetailDTO dto);
}
