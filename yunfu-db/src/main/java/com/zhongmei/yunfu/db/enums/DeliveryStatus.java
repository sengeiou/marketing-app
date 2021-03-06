package com.zhongmei.yunfu.db.enums;

import com.zhongmei.yunfu.util.ValueEnum;

/**
 * 送餐状态
 */
public enum DeliveryStatus implements ValueEnum<Integer> {

    /**
     * 等待配送
     */
    WAITINT_DELIVERY(0),

    /**
     * 正在配送
     */
    DELIVERYING(1),

    /**
     * 送餐完成
     */
    REAL_DELIVERY(2),

    /**
     * 已清账
     */
    SQUARE_UP(3),

    /**
     * 未知的值
     *
     * @deprecated 为了避免转为enum出错而设置，不应直接使用
     */
    @Deprecated
    __UNKNOWN__;

    private final Helper<Integer> helper;

    private DeliveryStatus(Integer value) {
        helper = Helper.valueHelper(value);
    }

    private DeliveryStatus() {
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
