package com.zhongmei.bty.basemodule.customer.enums;

import com.zhongmei.yunfu.util.ValueEnum;

/**
 *

 *
 */
public enum CouponRuleType implements ValueEnum<Integer> {

    /**
     * 固定金额
     */
    FIXED_AMOUNT(1),
    /**
     * 区间反比
     */
    INVERSE_RANGE(2),

    /**
     * 未知的值
     *
     * @deprecated 为了避免转为enum出错而设置，不应直接使用
     */
    @Deprecated
    __UNKNOWN__;

    private final Helper<Integer> helper;

    private CouponRuleType(Integer value) {
        helper = Helper.valueHelper(value);
    }

    private CouponRuleType() {
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
