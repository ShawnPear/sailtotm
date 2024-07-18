package com.yasyl.sailtotm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesName {
    private String properties;
    private TranslatorDict propertiesNameItem;
}
