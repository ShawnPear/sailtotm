package com.yasyl.sailtotm.infra.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaobaoGoodDetailRawJsonVO {
    String detailJson;
}