package com.yasyl.sailtotm.service.impl;

import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.StoreLocationDTO;
import com.yasyl.sailtotm.entity.StoreLocation;
import com.yasyl.sailtotm.exception.user.IdExistException;
import com.github.pagehelper.PageHelper;
import com.yasyl.sailtotm.mapper.StoreLocationMapper;
import com.yasyl.sailtotm.service.StoreLocationService;
import com.yasyl.sailtotm.vo.StoreLocationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreLocationServiceImpl implements StoreLocationService {

    @Autowired
    StoreLocationMapper mapper;

    @Override
    public Boolean add(StoreLocationDTO dto) {
        StoreLocation location = StoreLocation.builder().build();
        BeanUtils.copyProperties(dto, location);
        try {
            Boolean status = mapper.add(location);
            return status;
        } catch (Exception e) {
            throw new IdExistException(MessageConstant.ID_EXIST_ERROR);
        }
    }

    @Override
    public Boolean update(StoreLocationDTO dto) {
        StoreLocation location = StoreLocation.builder().build();
        BeanUtils.copyProperties(dto, location);
        try {
            Boolean status = mapper.update(location);
            return status;
        } catch (Exception e) {
            throw new IdExistException(MessageConstant.ID_NOT_EXIST_ERROR);
        }
    }

    @Override
    public List<StoreLocationVO> getAll() {
        List<StoreLocation> all = mapper.getAll();
        List<StoreLocationVO> list = new ArrayList<>();
        for (StoreLocation location : all) {
            StoreLocationVO vo = StoreLocationVO.builder().build();
            BeanUtils.copyProperties(location, vo);
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<StoreLocationVO> getAllPage(String page, String pageSize) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<StoreLocation> all = mapper.getAllPage();
        List<StoreLocationVO> list = new ArrayList<>();
        for (StoreLocation location : all) {
            StoreLocationVO vo = StoreLocationVO.builder().build();
            BeanUtils.copyProperties(location, vo);
            list.add(vo);
        }
        return list;
    }

    @Override
    public StoreLocationVO getAllById(String id) {
        StoreLocation all = mapper.getAllById(id);
        StoreLocationVO vo = StoreLocationVO.builder().build();
        BeanUtils.copyProperties(all, vo);
        return vo;
    }
}
