package com.service.impl;

import com.dto.TransportTypeDTO;
import com.entity.TransportType;
import com.mapper.TransportTypeMapper;
import com.service.TransportTypeService;
import com.vo.TransportTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransportTypeServiceImpl implements TransportTypeService {
    @Autowired
    TransportTypeMapper mapper;

    @Override
    public List<TransportTypeVO> getAll() {
        List<TransportType> all = mapper.getAll();
        List<TransportTypeVO> ans = new ArrayList<>();
        for (TransportType transportType : all) {
            ans.add(TransportTypeVO.builder()
                    .transport_id(transportType.getTransportId())
                    .transport(transportType.getTransport())
                    .updated_date(transportType.getUpdatedDate().toString())
                    .price(transportType.getPrice())
                    .build());
        }
        return ans;
    }

    @Override
    public Boolean add(TransportTypeDTO dto) {
        Boolean status = mapper.add(TransportType.builder()
                .transport(dto.getTransport())
                .price(dto.getPrice())
                .updatedDate(LocalDateTime.now())
                .build());
        return status;
    }

    @Override
    public Boolean update(TransportTypeDTO dto) {
        TransportType build = TransportType.builder()
                .transportId(dto.getTransportId())
                .updatedDate(LocalDateTime.now())
                .build();
        if (dto.getTransport() != null && !dto.getTransport().isEmpty()) build.setTransport(dto.getTransport());
        if (dto.getPrice() != null && dto.getPrice() > 0) build.setPrice(dto.getPrice());
        Boolean status = mapper.update(build);
        return status;
    }
}
