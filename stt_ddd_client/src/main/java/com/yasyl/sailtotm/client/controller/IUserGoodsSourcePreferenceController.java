package com.yasyl.sailtotm.client.controller;

import com.yasyl.sailtotm.client.dto.request.GoodSourceIncrDTO;
import com.yasyl.sailtotm.client.dto.response.GoodSourceRateDTO;
import com.yasyl.sailtotm.client.dto.result.Result;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-20 00:43
 **/
public interface IUserGoodsSourcePreferenceController {

    Result<GoodSourceRateDTO> calUserGoodsSource(String user_id);

    Result incrUserGoodsSource( GoodSourceIncrDTO goodSourceIncrDTO);
}
