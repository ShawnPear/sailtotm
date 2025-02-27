package com.yasyl.sailtotm.service.impl;

import com.yasyl.sailtotm.constant.MessageConstant;
import com.yasyl.sailtotm.dto.ChinaDeliverDetailDTO;
import com.yasyl.sailtotm.entity.ChinaDeliverDetail;
import com.yasyl.sailtotm.exception.user.ParamMissingException;
import com.yasyl.sailtotm.mapper.ChinaDeliverDetailMapper;
import com.yasyl.sailtotm.service.ChinaDeliverDetailService;
import com.yasyl.sailtotm.vo.ChinaDeliverDetailVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChinaDeliverDetailServiceImpl implements ChinaDeliverDetailService {
    @Autowired
    ChinaDeliverDetailMapper chinaDeliverDetailMapper;

    @Override
    public List<ChinaDeliverDetailVO> getAll() {
        List<ChinaDeliverDetail> chinaDeliverDetails = chinaDeliverDetailMapper.selectAllPage();
        List<ChinaDeliverDetailVO> list = new ArrayList<>();
        for (ChinaDeliverDetail detail : chinaDeliverDetails) {
            ChinaDeliverDetailVO build = ChinaDeliverDetailVO.builder().build();
            BeanUtils.copyProperties(detail, build);
            list.add(build);
        }
        return list;
    }

    @Override
    public Boolean add(ChinaDeliverDetailDTO dto) {
        ChinaDeliverDetail detail = ChinaDeliverDetail.builder().build();
        BeanUtils.copyProperties(dto, detail);
        Boolean status = chinaDeliverDetailMapper.insert(detail);
        return status;
    }

    @Override
    public Boolean update(ChinaDeliverDetailDTO dto) {
        ChinaDeliverDetail detail = ChinaDeliverDetail.builder().deliverId(dto.getDeliverId()).build();
        if (dto.getName() != null && !dto.getName().isEmpty()) detail.setName(dto.getName());
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isEmpty())
            detail.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getStatus() != null && !dto.getStatus().isEmpty()) detail.setStatus(Integer.valueOf(dto.getStatus()));
        if (dto.getAddress() != null && !dto.getAddress().isEmpty()) detail.setAddress(dto.getAddress());
        try {
            Boolean status = chinaDeliverDetailMapper.updateById(detail);
            return status;
        } catch (Exception e) {
            throw new ParamMissingException(MessageConstant.PARAM_MISSING_ERROR);
        }
    }
}
