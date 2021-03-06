package com.zhongmei.yunfu.db.enums;

import com.zhongmei.yunfu.util.ValueEnum;

/**
 * @version: 1.0
 * @date 2016年3月9日
 */
public enum PrintStatus implements ValueEnum<Integer> {

    /**
     * 打印中
     */
    PRINTING(1),
    /**
     * 已打印
     */
    FINISHED(2),
    /**
     * 打印失败
     */
    FAILED(3),
    /**
     * 未打印
     */
    UNPRINT(4),

    /**
     * 未知的值
     *
     * @deprecated 为了避免转为enum出错而设置，不应直接使用
     */
    @Deprecated
    __UNKNOWN__;

    private final Helper<Integer> helper;

    private PrintStatus(Integer value) {
        helper = Helper.valueHelper(value);
    }

    private PrintStatus() {
        helper = Helper.unknownHelper();
    }

    @Override
    public Integer value() {
        return helper.value();
    }

    @Override
    public boolean equalsValue(Integer value) {
        return helper.equalsValue(this, value);
    }

    @Override
    public boolean isUnknownEnum() {
        return helper.isUnknownEnum();
    }

    @Override
    public void setUnknownValue(Integer value) {
        helper.setUnknownValue(value);
    }

    @Override
    public String toString() {
        return "" + value();
    }

}
