package com.macro.mall.enums;

public enum Gender {
    UNKNOWN(0, "未知"),
    MAN(1, "先生"),
    WOMAN(2, "女士");

    private int value;
    private String name;

    Gender(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
