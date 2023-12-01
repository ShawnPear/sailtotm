package com.service;

import com.dto.TransportTypeDTO;
import com.vo.TransportTypeVO;

import java.util.List;

public interface TransportTypeService {
    public List<TransportTypeVO> getAll();

    public Boolean add(TransportTypeDTO dto);

    public Boolean update(TransportTypeDTO dto);
}
