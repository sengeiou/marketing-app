package com.zhongmei.bty.data.operates.message.content;

import com.zhongmei.yunfu.db.entity.trade.TradeUser;
import com.zhongmei.yunfu.db.entity.trade.Trade;
import com.zhongmei.yunfu.db.entity.trade.TradeExtra;
import com.zhongmei.bty.commonmodule.database.entity.TradeInitConfig;
import com.zhongmei.bty.commonmodule.database.entity.TradeTax;

import java.util.List;

/**
 * 开台请求
 * <p>
 * Created by demo on 2018/12/15
 */
public class BookingGroupOpenReq extends Trade {

    public TradeExtra tradeExtra;

    public BookingGroupOpenInfoReq bookingInfo;

    public TradeUser tradeUser;

    public List<TradeTax> tradeTaxs;
    public List<TradeInitConfig> tradeInitConfigs;

//    public BookingGroupOpenContentReq(){
//        setBrandIdenty(BaseApplication.getInstance().getBrandIdenty());
//        setShopIdenty(BaseApplication.getInstance().getShopIdenty());
//        setDeviceIdenty(BaseApplication.getInstance().getDeviceIdenty());
//    }

}
