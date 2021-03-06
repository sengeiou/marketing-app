package com.zhongmei.yunfu.db.enums;

import com.zhongmei.yunfu.util.ValueEnum;

/**
 * 菜品属性类型
 */
public enum PropertyKind implements ValueEnum<Integer> {

    /**
     * 口味，做法
     */
    PROPERTY(1),
    /**
     * 标签
     */
    LABEL(2),
    /**
     * 备注
     */
    MEMO(3),
    /**
     * 规格类属性
     */
    STANDARD(4),
    /**
     * 未知的值
     *
     * @deprecated 为了避免转为enum出错而设置，不应直接使用
     */
    @Deprecated
    __UNKNOWN__;

    private final Helper<Integer> helper;

    private PropertyKind(Integer value) {
        helper = Helper.valueHelper(value);
    }

    private PropertyKind() {
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
