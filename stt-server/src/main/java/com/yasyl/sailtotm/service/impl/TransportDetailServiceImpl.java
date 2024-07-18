package com.yasyl.sailtotm.service.impl;

import com.yasyl.sailtotm.dto.TransportDetailDTO;
import com.yasyl.sailtotm.exception.user.ParamMissingException;
import com.yasyl.sailtotm.mapper.TransportDetailMapper;
import com.yasyl.sailtotm.service.TransportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yasyl.sailtotm.constant.MessageConstant.PARAM_MISSING_ERROR;

@Service
public class TransportDetailServiceImpl implements TransportDetailService {

    @Autowired
    TransportDetailMapper transportDetailMapper;

    @Override
    public Boolean modifySize(TransportDetailDTO dto) {
        Integer id;
        Integer width;
        Integer height;
        Integer length;
        Double weight;
        try {
            id = Integer.valueOf(dto.getOrderId());
            width = Integer.valueOf(dto.getWidth());
            height = Integer.valueOf(dto.getHeight());
            length = Integer.valueOf(dto.getLength());
            weight = Double.valueOf(dto.getWeight());
        } catch (Exception e) {
            throw new ParamMissingException(PARAM_MISSING_ERROR);
        }
        Boolean status = transportDetailMapper.modifySize(id, width, height, length, weight);
        return status;
    }
}
