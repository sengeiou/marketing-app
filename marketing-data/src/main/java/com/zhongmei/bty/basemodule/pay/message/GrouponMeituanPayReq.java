package com.zhongmei.bty.basemodule.pay.message;

import com.zhongmei.yunfu.db.entity.trade.PaymentItem;

import java.math.BigDecimal;

public class GrouponMeituanPayReq extends PaymentItemReq {
    private GrouponMeituanPayReqItem paymentItemGroupon;//券信息

    public GrouponMeituanPayReq(PaymentItem paymentItem) {
        super(paymentItem);
        GrouponMeituanPayReqItem grouponMeituanPayReqItem = new GrouponMeituanPayReqItem();
        grouponMeituanPayReqItem.setDealTitle(paymentItem.getPaymentItemGroupon().getDealTitle());
        grouponMeituanPayReqItem.setGrouponId(paymentItem.getPaymentItemGroupon().getGrouponId());
        grouponMeituanPayReqItem.setMarketPrice(paymentItem.getPaymentItemGroupon().getMarketPrice());
        grouponMeituanPayReqItem.setPrice(paymentItem.getPaymentItemGroupon().getPrice());
        grouponMeituanPayReqItem.setSerialNo(paymentItem.getPaymentItemGroupon().getSerialNo());
        grouponMeituanPayReqItem.setUseCount(paymentItem.getPaymentItemGroupon().getUseCount());
        paymentItemGroupon = grouponMeituanPayReqItem;
    }

    public GrouponMeituanPayReqItem getPaymentItemGroupon() {
        return paymentItemGroupon;
    }

    public void setPaymentItemGroupon(GrouponMeituanPayReqItem paymentItemGroupon) {
        this.paymentItemGroupon = paymentItemGroupon;
    }

    public class GrouponMeituanPayReqItem {
        private String dealTitle;//团单名称
        private Long grouponId;//团购groupon.id
        private BigDecimal marketPrice;//市场价(元)
        private BigDecimal price;//销售价(元)
        private String serialNo;//团购券码
        private Integer useCount;//准备用掉的团购券张数

        public String getDealTitle() {
            return dealTitle;
        }

        public void setDealTitle(String dealTitle) {
            this.dealTitle = dealTitle;
        }

        public Long getGrouponId() {
            return grouponId;
        }

        public void setGrouponId(Long grouponId) {
            this.grouponId = grouponId;
        }

        public BigDecimal getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(BigDecimal marketPrice) {
            this.marketPrice = marketPrice;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public Integer getUseCount() {
            return useCount;
        }

        public void setUseCount(Integer useCount) {
            this.useCount = useCount;
        }
    }
}
