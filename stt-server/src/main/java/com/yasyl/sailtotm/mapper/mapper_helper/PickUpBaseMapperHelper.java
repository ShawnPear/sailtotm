package com.yasyl.sailtotm.mapper.mapper_helper;

import com.yasyl.sailtotm.mapper.PickUpBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PickUpBaseMapperHelper {
    @Autowired
    PickUpBaseMapper mapper;

    @Transactional
    public Integer usePickUpCode() {
        Integer pickUpCode = mapper.getPickUpCode();
        Boolean status = mapper.changeEnable(pickUpCode, Boolean.FALSE);
        return status ? pickUpCode : null;
    }

    @Transactional
    public Boolean pourPickUpCode(Integer code) {
        return mapper.changeEnable(code, Boolean.TRUE);
    }

}
