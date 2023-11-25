package com.service;

import com.dto.StuffLoginDTO;
import com.dto.StuffRegisterDTO;
import com.entity.Stuff;

public interface StuffService {
    Stuff login(StuffLoginDTO stuffLoginDTO);

    Boolean register(StuffRegisterDTO stuffRegisterDTO);
}
