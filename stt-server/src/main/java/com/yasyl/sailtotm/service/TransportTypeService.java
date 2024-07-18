package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.TransportTypeDTO;
import com.yasyl.sailtotm.vo.TransportTypeVO;

import java.util.List;

public interface TransportTypeService {
    public List<TransportTypeVO> getAll();

    public Boolean add(TransportTypeDTO dto);

    public Boolean update(TransportTypeDTO dto);
}
