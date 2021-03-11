package com.macro.mall.bo;

/**
 * 系统字典表，存放已确定的枚举类值
 */
public class SystemDictionary {

    // 订单流程状态，0-未开始，1-进行中，2-已结束，3-已终止
    public static final int PROCESS_NOT_START = 0;
    public static final int PROCESS_ON = 1;
    public static final int PROCESS_FINISHED = 2;
    public static final int PROCESS_ABORT = 3;

    // 步骤配置的字段类型，0-未开始，1-进行中，2-已结束，3-已终止
    public static final int FIELD_TYPE_TEXT = 0;
    public static final int FIELD_TYPE_AMOUNT = 1;
    public static final int FIELD_TYPE_CHECKBOX = 2;
    public static final int FIELD_TYPE_DATE = 3;
    public static final int FIELD_TYPE_DATETIME = 3;




}
