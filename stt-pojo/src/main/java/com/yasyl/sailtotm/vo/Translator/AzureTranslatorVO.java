package com.yasyl.sailtotm.vo.Translator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AzureTranslatorVO {
    private List<Translation> translations;
}