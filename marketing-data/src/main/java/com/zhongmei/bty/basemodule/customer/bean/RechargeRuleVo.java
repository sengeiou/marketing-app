package com.zhongmei.bty.basemodule.customer.bean;

import java.math.BigDecimal;
import java.util.List;

import com.zhongmei.bty.basemodule.devices.mispos.data.EcCardSettingDetail;
import com.zhongmei.yunfu.db.entity.crm.CrmLevelStoreRuleDetail;
import com.zhongmei.bty.basemodule.customer.enums.FullSend;
import com.zhongmei.bty.commonmodule.database.enums.SendType;

/**
 * 充值规则Vo
 */
public class RechargeRuleVo {

    /**
     * 是否满赠
     */
    private FullSend isFullSend;

    /**
     * 赠送类型
     */
    private SendType sendType;

    /**
     * 赠送规则明细
     */
    private List<RechargeRuleDetailVo> RuleDetailList;

    public FullSend getIsFullSend() {
        return isFullSend;
    }

    public void setIsFullSend(FullSend isFullSend) {
        this.isFullSend = isFullSend;
    }

    public SendType getSendType() {
        return sendType;
    }

    public void setSendType(SendType sendType) {
        this.sendType = sendType;
    }

    public List<RechargeRuleDetailVo> getRuleDetailList() {
        return RuleDetailList;
    }

    public void setRuleDetailList(List<RechargeRuleDetailVo> ruleDetailList) {
        RuleDetailList = ruleDetailList;
    }

    public static class RechargeRuleDetailVo {

        /**
         * 满值
         */
        private BigDecimal fullValue;

        /**
         * 赠送金额
         */
        private BigDecimal sendValue;

        /**
         * 赠送百分比
         */
        private BigDecimal sendRate;

        /**
         * 实体卡充值规则映射
         *
         * @param ecCardSettingDetail
         */
        public void setRule(EcCardSettingDetail ecCardSettingDetail) {
            this.fullValue = ecCardSettingDetail.getFullValue();
            this.sendValue = ecCardSettingDetail.getSendValue();
            this.sendRate = ecCardSettingDetail.getSendRate();

        }

        public void setRule(CrmLevelStoreRuleDetail customerRuleDetail) {
            this.fullValue = customerRuleDetail.getFullValue();
            this.sendValue = customerRuleDetail.getSendValue();
            this.sendRate = customerRuleDetail.getRate();
        }

        public BigDecimal getFullValue() {
            return fullValue;
        }

        public void setFullValue(BigDecimal fullValue) {
            this.fullValue = fullValue;
        }

        public BigDecimal getSendValue() {
            return sendValue;
        }

        public void setSendValue(BigDecimal sendValue) {
            this.sendValue = sendValue;
        }

        public BigDecimal getSendRate() {
            return sendRate;
        }

        public void setSendRate(BigDecimal sendRate) {
            this.sendRate = sendRate;
        }
    }

}
