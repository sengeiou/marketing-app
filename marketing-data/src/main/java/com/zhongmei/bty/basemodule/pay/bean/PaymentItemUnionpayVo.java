package com.zhongmei.bty.basemodule.pay.bean;

import com.zhongmei.bty.basemodule.database.entity.pay.PaymentDevice;
import com.zhongmei.bty.basemodule.database.entity.pay.PaymentItemUnionpay;

public class PaymentItemUnionpayVo implements java.io.Serializable {

    /**
     * @date：2016-2-24 下午2:07:51
     * @Description:TODO
     */
    private static final long serialVersionUID = 1L;

    private PaymentItemUnionpay paymentItemUnionpay;

    private PaymentDevice paymentDevice;

    public PaymentItemUnionpay getPaymentItemUnionpay() {
        return paymentItemUnionpay;
    }

    public void setPaymentItemUnionpay(PaymentItemUnionpay paymentItemUnionpay) {
        this.paymentItemUnionpay = paymentItemUnionpay;
    }

    public PaymentDevice getPaymentDevice() {
        return paymentDevice;
    }

    public void setPaymentDevice(PaymentDevice paymentDevice) {
        this.paymentDevice = paymentDevice;
    }
}
