package com.yasyl.sailtotm.service;

import com.yasyl.sailtotm.dto.Stuff.StuffLoginDTO;
import com.yasyl.sailtotm.dto.Stuff.StuffRegisterDTO;
import com.yasyl.sailtotm.dto.Stuff.StuffStatusChangeDTO;
import com.yasyl.sailtotm.entity.Stuff;

public interface StuffService {
    Stuff login(StuffLoginDTO stuffLoginDTO);
    Boolean register(StuffRegisterDTO stuffRegisterDTO);
    Boolean changeStuffStatus(StuffStatusChangeDTO dto);
}
