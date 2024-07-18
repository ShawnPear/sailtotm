package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.ChinaDeliverDetailDTO;
import com.yasyl.sailtotm.vo.ChinaDeliverDetailVO;

import java.util.List;

public interface ChinaDeliverDetailService {
    public List<ChinaDeliverDetailVO> getAll();

    public Boolean add(ChinaDeliverDetailDTO dto);

    public Boolean update(ChinaDeliverDetailDTO dto);
}
