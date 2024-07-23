package com.yasyl.sailtotm.domain.translation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorDictDO {
    private Integer translatorId;
    private String zh;
    private String ru;
}