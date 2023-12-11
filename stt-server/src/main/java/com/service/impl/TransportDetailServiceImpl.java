package com.service.impl;

import com.dto.TransportDetailDTO;
import com.exception.user.ParamMissingException;
import com.mapper.TransportDetailMapper;
import com.service.TransportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.constant.MessageConstant.PARAM_MISSING_ERROR;

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
