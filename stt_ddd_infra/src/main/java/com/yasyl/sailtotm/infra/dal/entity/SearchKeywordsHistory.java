package com.yasyl.sailtotm.infra.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SailToTm
 * @description:
 * @author: wujubin
 * @create: 2024-07-24 10:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchKeywordsHistory {
    private long userId;
    private String keyword;
    private int cnt;
    private int ctime;
    private int utime;
}
