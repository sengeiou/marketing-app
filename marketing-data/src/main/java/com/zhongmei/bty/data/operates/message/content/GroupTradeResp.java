package com.zhongmei.bty.data.operates.message.content;

import com.zhongmei.yunfu.db.entity.trade.TradeUser;
import com.zhongmei.yunfu.db.entity.trade.Trade;
import com.zhongmei.yunfu.db.entity.trade.TradeCustomer;
import com.zhongmei.yunfu.db.entity.trade.TradeExtra;
import com.zhongmei.bty.commonmodule.database.entity.TradeInitConfig;
import com.zhongmei.yunfu.db.entity.trade.TradeItem;
import com.zhongmei.yunfu.db.entity.trade.TradeTable;
import com.zhongmei.bty.basemodule.trade.bean.TradeGroupInfo;
import com.zhongmei.bty.commonmodule.database.entity.TradeTax;

import java.util.List;

/**
 * 团餐开台Trade回执
 * <p>
 * Created by demo on 2018/12/15
 */
public class GroupTradeResp {

    public Trade trade;

    public TradeExtra tradeExtra;

    public List<TradeCustomer> tradeCustomers;

    public List<TradeItem> tradeItems;

    public List<TradeTable> tradeTables;

    public TradeGroupInfo tradeGroup;  // v7.15 添加团餐信息表
    public TradeUser tradeUser;

    public List<TradeTax> tradeTaxs;
    public List<TradeInitConfig> tradeInitConfigs;
}
