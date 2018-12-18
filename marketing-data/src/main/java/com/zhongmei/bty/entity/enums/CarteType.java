package com.zhongmei.bty.entity.enums;

import com.zhongmei.yunfu.util.ValueEnum;

/**
 * 菜单类型
 * Created by demo on 2018/12/15
 */

public enum CarteType implements ValueEnum<Integer> {

    /**
     * 自助餐
     */
    AUTO_BUFFER(1),

    /**
     * 团餐
     */
    GROUP_DINNER(2),

    /**
     * 未知的值
     *
     * @deprecated 为了避免转为enum出错而设置，不应直接使用
     */
    @Deprecated
    __UNKNOWN__;

    private final Helper<Integer> helper;

    private CarteType(Integer value) {
        helper = Helper.valueHelper(value);
    }

    private CarteType() {
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