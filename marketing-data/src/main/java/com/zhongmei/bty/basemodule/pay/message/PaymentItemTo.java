package com.zhongmei.bty.basemodule.pay.message;

import com.zhongmei.yunfu.db.entity.trade.PaymentItem;

/**
 * v1支付明细请求专用
 * Created by demo on 2018/12/15
 */

public class PaymentItemTo extends PaymentItem {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || super.equals(obj);
    }
}
