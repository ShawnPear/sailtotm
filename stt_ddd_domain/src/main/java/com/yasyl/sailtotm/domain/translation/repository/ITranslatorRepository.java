package com.yasyl.sailtotm.domain.translation.repository;

import com.yasyl.sailtotm.common.enumeration.TranslatorType;

import java.io.IOException;
import java.util.List;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-23 23:18
 **/
public interface ITranslatorRepository {
    List<String> translator(TranslatorType type, String[] qArray) throws IOException;
}
