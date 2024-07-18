package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.StoreLocationDTO;
import com.yasyl.sailtotm.vo.StoreLocationVO;

import java.util.List;

public interface StoreLocationService {
    public Boolean add(StoreLocationDTO dto);

    public Boolean update(StoreLocationDTO dto);

    public List<StoreLocationVO> getAll();

    List<StoreLocationVO> getAllPage(String page, String pageSize);

    StoreLocationVO getAllById(String id);
}
