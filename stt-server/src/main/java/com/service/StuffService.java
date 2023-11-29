package com.service;

import com.dto.Stuff.StuffLoginDTO;
import com.dto.Stuff.StuffRegisterDTO;
import com.dto.Stuff.StuffStatusChangeDTO;
import com.entity.Stuff;

public interface StuffService {
    Stuff login(StuffLoginDTO stuffLoginDTO);
    Boolean register(StuffRegisterDTO stuffRegisterDTO);
    Boolean changeStuffStatus(StuffStatusChangeDTO dto);
}
