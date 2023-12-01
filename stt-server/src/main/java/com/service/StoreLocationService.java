package com.service;

import com.dto.StoreLocationDTO;
import com.vo.StoreLocationVO;

import java.util.List;

public interface StoreLocationService {
    public Boolean add(StoreLocationDTO dto);

    public Boolean update(StoreLocationDTO dto);

    public List<StoreLocationVO> getAll();

    List<StoreLocationVO> getAllPage(String page, String pageSize);

    StoreLocationVO getAllById(String id);
}
