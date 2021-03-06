package com.zhongmei.bty.dinner.table.manager;

import android.text.TextUtils;

import com.zhongmei.yunfu.MainApplication;
import com.zhongmei.bty.basemodule.discount.bean.CouponPrivilegeVo;
import com.zhongmei.bty.basemodule.discount.bean.WeiXinCouponsVo;
import com.zhongmei.yunfu.db.entity.discount.TradeItemPlanActivity;
import com.zhongmei.yunfu.db.entity.discount.TradePrivilege;
import com.zhongmei.bty.basemodule.inventory.utils.InventoryUtils;
import com.zhongmei.bty.basemodule.orderdish.bean.TradeItemVo;
import com.zhongmei.yunfu.db.entity.trade.TradeItemExtra;
import com.zhongmei.yunfu.db.entity.dish.TradeItemOperation;
import com.zhongmei.bty.basemodule.orderdish.enums.ShopcartItemType;
import com.zhongmei.bty.basemodule.shoppingcart.DinnerShoppingCart;
import com.zhongmei.bty.basemodule.trade.bean.DinnertableTradeInfo;
import com.zhongmei.bty.basemodule.trade.bean.TradeVo;
import com.zhongmei.yunfu.db.entity.trade.TradeCustomer;
import com.zhongmei.bty.basemodule.trade.message.TradeUnionModifyMainWarpReq;
import com.zhongmei.bty.basemodule.trade.message.TradeUnionRequest;
import com.zhongmei.bty.basemodule.trade.message.uniontable.UnionAndModifyUnionTradeReq;
import com.zhongmei.yunfu.db.entity.trade.TradeItem;
import com.zhongmei.yunfu.db.entity.trade.TradeItemProperty;
import com.zhongmei.yunfu.db.entity.trade.TradeReasonRel;
import com.zhongmei.yunfu.db.enums.StatusFlag;
import com.zhongmei.yunfu.context.util.Utils;
import com.zhongmei.bty.dinner.manager.DinnerUnionManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by demo on 2018/12/15
 * 构建主单合单请求数据
 */

public class DinnerMergeUnionManager {

    public static UnionAndModifyUnionTradeReq createUnionTradeMergeReq(TradeVo tradeVo) {
        UnionAndModifyUnionTradeReq mergeReq = new UnionAndModifyUnionTradeReq();//合单请求
        //主单改单请求
        TradeUnionModifyMainWarpReq.TradeUnionModifyMainReq tradeUnionMainReq = new TradeUnionModifyMainWarpReq.TradeUnionModifyMainReq();
        //构建主单菜品
        List<TradeUnionModifyMainWarpReq.TradeUnionTradeItemReq> tradeItemReqList = new ArrayList<>();
        List<TradeReasonRel> tradeReasonRelList = new ArrayList<>();
        DinnerUnionManager.buildModiyItemReq(tradeVo, tradeItemReqList, null, tradeReasonRelList);
        //设置联桌的子单id
        TradeUnionModifyMainWarpReq.TradeUnionModifyRelReq tradeUnionModifyIdReq = new TradeUnionModifyMainWarpReq.TradeUnionModifyRelReq();
        tradeUnionModifyIdReq.setAddItemSubTradeIds(tradeVo.getSubTradeIdList());

        tradeUnionMainReq.setTradeRelRequest(tradeUnionModifyIdReq);
        tradeUnionMainReq.setTradeItems(tradeItemReqList);
        tradeUnionMainReq.setTradeExtra(tradeVo.getTradeExtra());
        //tradeUnionMainReq.setTradeCustomers(tradeVo.getTradeCustomerList());
        //tradeUnionMainReq.setTradePrivileges(tradeVo.getTradePrivileges());
        if (Utils.isNotEmpty(tradeVo.getTradeReasonRelList())) {
            tradeReasonRelList.addAll(tradeVo.getTradeReasonRelList());
        }
        tradeUnionMainReq.setTradeReasonRels(tradeReasonRelList);
        TradeUnionRequest mainTradeReq = DinnerUnionManager.covertTradeToUnionRequest(tradeVo.getTrade());
        mainTradeReq.setDeviceIdenty(MainApplication.getInstance().getDeviceIdenty());
        tradeUnionMainReq.setMainTrade(mainTradeReq);
        tradeUnionMainReq.setTradeTaxs(tradeVo.getTradeTaxs());
        //主单改单数据
        mergeReq.modifyRequest = tradeUnionMainReq;
        //设置库存信息
        InventoryUtils.setInventoryVoValue(tradeVo);
        mergeReq.inventoryRequest = InventoryUtils.makeInventoryChangeReq(tradeVo.inventoryVo);
        //主单合单数据
        mergeReq.unionTradeModifyRequest = createUnionTradeModifyReq(tradeVo);
        //子单列表
        mergeReq.unionRequest = getUnionReq();

        return mergeReq;
    }

    //从购物车获取子单列表
    private static UnionAndModifyUnionTradeReq.UnionReq getUnionReq() {
        List<UnionAndModifyUnionTradeReq.SubTrade> subTradeList = new ArrayList<UnionAndModifyUnionTradeReq.SubTrade>();
        UnionAndModifyUnionTradeReq.UnionReq unionReq = new UnionAndModifyUnionTradeReq.UnionReq();
        DinnertableTradeInfo tradeInfo = DinnerShoppingCart.getInstance().getCurrentTradeInfo();
        //遍历主单下所有的子单
        if (tradeInfo != null && tradeInfo.getSubTradeInfoList() != null) {
            for (DinnertableTradeInfo info : tradeInfo.getSubTradeInfoList()) {
                if (info.getTradeVo() != null && info.getTradeVo().getTrade() != null) {
                    UnionAndModifyUnionTradeReq.SubTrade subTrade = new UnionAndModifyUnionTradeReq.SubTrade();
                    subTrade.serverUpdateTime = info.getTradeVo().getTrade().getServerUpdateTime();
                    subTrade.tradeId = info.getTradeVo().getTrade().getId();
                    subTradeList.add(subTrade);
                }
            }
        }
        unionReq.tradeList = subTradeList;
        return unionReq;
    }

    //构建合单部分数据
    private static UnionAndModifyUnionTradeReq.UnionTradeModifyReq createUnionTradeModifyReq(TradeVo tradeVo) {

        UnionAndModifyUnionTradeReq.UnionTradeModifyReq unionTradeModifyRequest = new UnionAndModifyUnionTradeReq.UnionTradeModifyReq();
        TradeUnionRequest mainTradeReq = DinnerUnionManager.covertTradeToUnionRequest(tradeVo.getTrade());
        mainTradeReq.setDeviceIdenty(MainApplication.getInstance().getDeviceIdenty());
        unionTradeModifyRequest.mainTrade = mainTradeReq;
        unionTradeModifyRequest.tradeTables = tradeVo.getTradeTableList();
        unionTradeModifyRequest.tradeCustomers = tradeVo.getTradeCustomerList();
        unionTradeModifyRequest.tradePlanActivitys = tradeVo.getTradePlanActivityList();
        unionTradeModifyRequest.tradeItemPlanActivitys = tradeVo.getTradeItemPlanActivityList();
        unionTradeModifyRequest.tradeItemExtraDinners = tradeVo.getTradeItemExtraDinners();
        if (tradeVo.getTradeUser() != null && tradeVo.getTradeUser().isChanged()) {
            unionTradeModifyRequest.tradeUser = tradeVo.getTradeUser();
        }
        //构建子单菜品合并到主单数据
        setSubTradeItemsData(tradeVo, unionTradeModifyRequest);

        return unionTradeModifyRequest;
    }

    //构建子单菜品合并到主单数据
    public static void setSubTradeItemsData(TradeVo tradeVo, UnionAndModifyUnionTradeReq.UnionTradeModifyReq unionTradeModifyRequest) {
        if (tradeVo == null || unionTradeModifyRequest == null) return;
        Long tradeId = tradeVo.getTrade().getId();//主单id
        String tradeUuid = tradeVo.getTrade().getUuid();//主单id
        List<TradeItemVo> itemVos = tradeVo.getTradeItemList();
        if (Utils.isNotEmpty(itemVos)) {
            List<TradeItem> subTradeItems = new ArrayList<TradeItem>();
            List<TradeItemProperty> subTradeItemPropertys = new ArrayList<TradeItemProperty>();
            List<TradeItemOperation> subTradeItemOperations = new ArrayList<TradeItemOperation>();
            List<TradePrivilege> tradePrivileges = new ArrayList<TradePrivilege>();
            List<TradeItemExtra> subTradeItemExtras = new ArrayList<TradeItemExtra>();
            Set<Long> subTItemIdSet = new HashSet<Long>();//子单菜品id
            //整单优惠
            if (tradeVo.getTradePrivileges() != null) {
                for (TradePrivilege tp : tradeVo.getTradePrivileges()) {
                    tradePrivileges.add(tp);
                }
            }
            //整单优惠劵
            if (tradeVo.getCouponPrivilegeVoList() != null) {
                for (CouponPrivilegeVo couponPrivilegeVo : tradeVo.getCouponPrivilegeVoList()) {
                    if (couponPrivilegeVo != null) {
                        TradePrivilege tp = couponPrivilegeVo.getTradePrivilege();
                        if (!TextUtils.isEmpty(tp.getUuid()) || tp.getStatusFlag() == StatusFlag.VALID) {
                            tradePrivileges.add(tp);
                        }
                    }
                }
            }
            //整单宴请
            if (tradeVo.getBanquetVo() != null && tradeVo.getBanquetVo().getTradePrivilege() != null) {
                TradePrivilege tp = tradeVo.getBanquetVo().getTradePrivilege();
                if (tp.getId() != null || tp.getStatusFlag() == StatusFlag.VALID) {
                    tradePrivileges.add(tp);
                }
            }
            //整单积分抵现
            if (tradeVo.getIntegralCashPrivilegeVo() != null) {
                TradePrivilege tp = tradeVo.getIntegralCashPrivilegeVo().getTradePrivilege();
                //异步改为使用uuid判断
                if (!TextUtils.isEmpty(tp.getUuid()) || tp.getStatusFlag() == StatusFlag.VALID) {
                    tradePrivileges.add(tp);
                }
            }
            //整单微信卡券
            List<WeiXinCouponsVo> listWXC = tradeVo.getmWeiXinCouponsVo();
            if (listWXC != null) {
                for (WeiXinCouponsVo mWeiXinCouponsVo : listWXC) {
                    TradePrivilege tp = mWeiXinCouponsVo.getmTradePrivilege();
                    if (tp.getId() != null || mWeiXinCouponsVo.isActived()) {
                        tradePrivileges.add(tp);
                    }
                }
            }
            //处理菜品数据
            for (TradeItemVo itemVo : itemVos) {
                boolean valid = (itemVo.getTradeItem().getStatusFlag() == StatusFlag.VALID);
                //单菜优惠
                if (itemVo.getTradeItemPrivilege() != null) {
                    TradePrivilege tp = itemVo.getTradeItemPrivilege();
                    if (tp.getStatusFlag() == StatusFlag.VALID || tp.getId() != null || !TextUtils.isEmpty(tp.getUuid())) {
                        // 有效的商品对应的优惠
                        if (valid && tp.getStatusFlag() != StatusFlag.INVALID) {
                            tradePrivileges.add(tp);
                        }
                    }
                }
                //单菜礼品券
                if (itemVo.getCouponPrivilegeVo() != null && itemVo.getCouponPrivilegeVo().getTradePrivilege() != null) {
                    TradePrivilege tp = itemVo.getCouponPrivilegeVo().getTradePrivilege();
                    if (itemVo.getCouponPrivilegeVo().isActived()
                            || tp.getId() != null) {
                        // 有效的商品对应的优惠
                        if (valid && tp.getStatusFlag() != StatusFlag.INVALID) {
                            tradePrivileges.add(tp);
                        }
                    }
                }

                if (valid && itemVo.getShopcartItemType() == ShopcartItemType.MAINSUB) {

                    subTItemIdSet.add(itemVo.getTradeItem().getId());
                    if (itemVo.getTradeItem() != null)
                        subTradeItems.add(itemVo.getTradeItem());
                    if (Utils.isNotEmpty(itemVo.getTradeItemPropertyList()))
                        subTradeItemPropertys.addAll(itemVo.getTradeItemPropertyList());
                    if (itemVo.getTradeItemExtra() != null && itemVo.getTradeItemExtra().isValid())
                        subTradeItemExtras.add(itemVo.getTradeItemExtra());

                }
            }
            //清空子单菜品id start
            if (Utils.isNotEmpty(subTradeItems)) {
                for (TradeItem tradeItem : subTradeItems) {
                    tradeItem.setId(null);
                    tradeItem.setTradeId(tradeId);
                    tradeItem.setTradeUuid(tradeUuid);
                    tradeItem.setServerCreateTime(null);
                    tradeItem.setServerUpdateTime(null);
                }
            }
            unionTradeModifyRequest.tradeItems = subTradeItems;

            if (Utils.isNotEmpty(subTradeItemPropertys)) {
                for (TradeItemProperty itemProperty : subTradeItemPropertys) {
                    if (itemProperty.getTradeItemId() != null && subTItemIdSet.contains(itemProperty.getTradeItemId())) {
                        itemProperty.setTradeItemId(null);
                        itemProperty.setId(null);
                        itemProperty.setServerCreateTime(null);
                        itemProperty.setServerUpdateTime(null);
                    }
                }
            }
            unionTradeModifyRequest.tradeItemProperties = subTradeItemPropertys;

            if (Utils.isNotEmpty(subTradeItemExtras)) {
                for (TradeItemExtra tradeItemExtra : subTradeItemExtras) {
                    if (tradeItemExtra.getTradeItemId() != null && subTItemIdSet.contains(tradeItemExtra.getTradeItemId())) {
                        tradeItemExtra.setTradeItemId(null);
                        tradeItemExtra.setId(null);
                        tradeItemExtra.setServerCreateTime(null);
                        tradeItemExtra.setServerUpdateTime(null);
                    }
                }
            }
            unionTradeModifyRequest.tradeItemExtras = subTradeItemExtras;

            if (Utils.isNotEmpty(subTradeItemOperations)) {
                for (TradeItemOperation itemOperation : subTradeItemOperations) {
                    if (itemOperation.getTradeItemId() != null && subTItemIdSet.contains(itemOperation.getTradeItemId())) {
                        itemOperation.setTradeItemId(null);
                        itemOperation.setId(null);
                        itemOperation.setServerCreateTime(null);
                        itemOperation.setServerUpdateTime(null);
                    }
                }
                unionTradeModifyRequest.tradeItemOperations = subTradeItemOperations;
            }

            if (Utils.isNotEmpty(unionTradeModifyRequest.tradeItemPlanActivitys)) {
                for (TradeItemPlanActivity tradeItemPlanActivity : unionTradeModifyRequest.tradeItemPlanActivitys) {
                    if (tradeItemPlanActivity.getTradeItemId() != null && subTItemIdSet.contains(tradeItemPlanActivity.getTradeItemId())) {
                        tradeItemPlanActivity.setTradeItemId(null);
                        tradeItemPlanActivity.setId(null);
                        tradeItemPlanActivity.setServerCreateTime(null);
                        tradeItemPlanActivity.setServerUpdateTime(null);
                    }
                }
            }
            //处理优惠
            if (Utils.isNotEmpty(tradePrivileges)) {
                for (TradePrivilege tp : tradePrivileges) {
                    //如果不是主单优惠，要清空id和时间戳
                    if (subTItemIdSet.contains(tp.getTradeItemId()) || tp.getTradeId() != null && !tp.getTradeId().equals(tradeId)) {
                        tp.setId(null);
                        tp.setServerCreateTime(null);
                        tp.setServerUpdateTime(null);
                        tp.setTradeItemId(null);
                    }
                    tp.setTradeId(tradeId);
                    tp.setTradeUuid(tradeUuid);
                }
            }
            unionTradeModifyRequest.tradePrivileges = tradePrivileges;
        }
        //check 会员tradeId
        if (Utils.isNotEmpty(unionTradeModifyRequest.tradeCustomers)) {
            for (TradeCustomer customer : unionTradeModifyRequest.tradeCustomers) {
                if (customer.getTradeId() == null) {
                    customer.setTradeId(tradeId);
                }
            }
        }
    }
}
