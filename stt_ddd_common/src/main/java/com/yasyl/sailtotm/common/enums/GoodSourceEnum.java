package com.yasyl.sailtotm.common.enums;

/**
 * @program: SailToTm
 * @description: 商品来源类型
 * @author: wujubin
 * @create: 2024-07-20 01:30
 **/
public enum GoodSourceEnum {
    TB(1, "tb"),

    PDD(2, "pdd"),

    JD(3, "jd");

    private final int value;

    private final String name;

    GoodSourceEnum(int v, String n) {
        this.name = n;
        this.value = v;
    }

    public static GoodSourceEnum findByValue(int value) {
        switch (value) {
            case 1:
                return TB;
            case 2:
                return PDD;
            case 3:
                return JD;
            default:
                return TB;
        }
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
