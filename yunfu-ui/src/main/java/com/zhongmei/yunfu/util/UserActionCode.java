package com.zhongmei.yunfu.util;

/**
 * Created by demo on 2018/12/15
 */

public enum UserActionCode {

    /***正餐代码定义***/
    ZC010001("桌台主界面-桌台页-桌台详情"),
    ZC010002("桌台主界面-桌台页-开台"),
    ZC010003("桌台主界面-桌台页-清台"),
    ZC010004("桌台主界面-桌台页-空闲桌台"),
    ZC010005("桌台主界面-桌台页-联台单入口"),
    ZC010006("桌台主界面-桌台页-更多功能"),
    ZC010007("桌台主界面-桌台页-转台"),
    ZC010008("桌台主界面-桌台页-合单"),
    ZC010009("桌台主界面-桌台页-更多功能-桌台流水号展示"),
    ZC010010("桌台主界面-桌台页-更多功能-已打印预结单提示"),
    ZC010011("桌台主界面-桌台页-更多功能-点餐／更新时间提示"),
    ZC010012("桌台主界面-桌台页-更多功能-按菜品搜索桌台"),
    ZC010013("桌台主界面-桌台页-更多功能-实时概况"),
    ZC010014("桌台主界面-桌台详情页-桌台区域切换"),
    ZC010015("桌台主界面-桌台详情页-一桌多单开台"),
    ZC010016("桌台主界面-桌台详情页-多订单查看"),
    ZC010017("桌台主界面-桌台详情页-联台单查看"),
    ZC010018("桌台主界面-桌台详情页-就餐人数UI弹起"),
    ZC010019("桌台主界面-桌台详情页-就餐人数修改确定"),
    ZC010020("桌台主界面-桌台详情页-就餐人数修改取消"),
    ZC010021("桌台主界面-桌台详情页-选择服务员"),
    ZC010022("桌台主界面-桌台详情页-选择销售员"),
    ZC010023("桌台主界面-桌台详情页-结账按钮"),
    ZC010024("桌台主界面-桌台详情页-点单按钮"),
    ZC010025("桌台主界面-桌台详情页-取消按钮"),
    ZC010026("桌台主界面-桌台详情页-保存按钮"),
    ZC010027("桌台主界面-桌台详情页-拒绝按钮"),
    ZC010028("桌台主界面-桌台详情页-接受按钮"),
    ZC010029("桌台主界面-桌台详情页-删除订单"),
    ZC010030("桌台主界面-桌台详情页-订单完成按钮"),
    ZC010031("桌台主界面-桌台详情页-订单操作按钮"),
    ZC010032("桌台主界面-桌台详情页-移菜"),
    ZC010033("桌台主界面-桌台详情页-复制"),
    ZC010034("桌台主界面-桌台详情页-起菜"),
    ZC010035("桌台主界面-桌台详情页-催菜"),
    ZC020001("点单主界面-主页-返回"),
    ZC020002("点单主界面-主页-传送后厨"),
    ZC020003("点单主界面-主页-去收银"),
    ZC020004("点单主界面-主页-修改人数与服务员"),
    ZC020005("点单主界面-主页-等叫"),
    ZC020006("点单主界面-主页-起菜"),
    ZC020007("点单主界面-主页-催菜"),
    ZC020008("点单主界面-主页-整单备注"),
    ZC020009("点单主界面-主页-批量操作"),
    ZC020010("点单主界面-主页-估清列表"),
    ZC020011("点单主界面-主页-扫码"),
    ZC020012("点单主界面-主页-临时菜"),
    ZC020013("点单主界面-主页-搜索"),
    ZC020014("点单主界面-估清弹窗-批量上架"),
    ZC020015("点单主界面-菜品操作-菜品操作栏修改"),
    ZC020016("点单主界面-菜品操作-菜品操作栏等叫"),
    ZC020017("点单主界面-菜品操作-菜品操作栏起菜"),
    ZC020018("点单主界面-菜品操作-菜品操作栏删除"),
    ZC020019("点单主界面-菜品操作-菜品操作栏暂停出单"),
    ZC020020("点单主界面-菜品操作-菜品操作栏关闭"),
    ZC020021("点单主界面-菜品操作-菜品操作栏赠送"),
    ZC020022("点单主界面-菜品操作-作废商品"),
    ZC020023("点单主界面-菜品操作-重打厨打"),
    ZC020024("点单主界面-菜品操作-重打堂口"),
    ZC020025("点单主界面-主页-取消等叫"),
    ZC020026("点单主界面-主页-取消起菜"),
    ZC020027("点单主界面-主页-菜品中类上翻"),
    ZC020028("点单主界面-主页-菜品中类下翻"),
    ZC020029("点单主界面-菜品操作-菜品操作栏取消等叫"),
    ZC020030("点单主界面-菜品操作-菜品操作栏取消等叫"),
    ZC030001("支付主界面-主页-返回"),
    ZC030002("支付主界面-主页-拆单支付"),
    ZC030003("支付主界面-主页-预结单"),
    ZC030004("支付主界面-主页-顾客登录、注册"),
    ZC030005("支付主界面-主页-折扣"),
    ZC030006("支付主界面-主页-附加费"),
    ZC030007("支付主界面-主页-微信卡劵"),
    ZC030008("支付主界面-主页-优惠活动"),
    ZC030009("支付主界面-主页-保存结算信息"),
    ZC030010("支付主界面-主页-抹零"),
    ZC030011("支付主界面-主页-发票"),
    ZC030012("支付主界面-主页-清空折扣"),
    ZC030013("支付主界面-折扣页-移除优惠"),
    ZC030014("支付主界面-折扣页-免单"),
    ZC030015("支付主界面-折扣页-宴请"),
    ZC030016("支付主界面-折扣页-自定义打折"),
    ZC030017("支付主界面-会员登录-刷卡登录"),
    ZC030018("支付主界面-会员登录-人脸登录"),
    ZC030019("支付主界面-会员登录-注册"),
    ZC030020("支付主界面-会员登录-会员实体卡切换"),
    ZC030021("支付主界面-会员登录-会员退出"),
    ZC030022("支付主界面-会员登录-积分"),
    ZC030023("支付主界面-会员登录-优惠劵"),
    ZC030024("支付主界面-会员登录-人脸"),
    ZC030025("支付主界面-会员登录-结账"),


    /***自助餐代码定义***/
    ZZC010001("有桌自助餐主界面-有桌自助餐桌台-显示结账状态"),
    ZZC010002("有桌自助餐主界面-有桌自助餐桌台-退押金按钮"),
    ZZC010003("有桌自助餐主界面-自助餐桌台-详情页-订单完成并退押金"),
    ZZC010004("有桌自助餐主界面-自助餐桌台-详情页-订单完成"),
    ZZC010005("有桌自助餐主界面-有桌自助餐-详情页-修改人数信息-自助餐套餐修改确定"),
    ZZC010006("有桌自助餐主界面-有桌自助餐-详情页-修改人数信息-自助餐套餐修改取消"),
    ZZC010007("有桌自助餐主界面-有桌自助餐-详情页-修改人数信息-更改服务员"),
    ZZC010008("有桌自助餐主界面-有桌自助餐-详情页-修改人数信息-更改销售员"),
    ZZC020001("无桌自助餐主界面-无桌自助餐主界面-扫码退押金"),
    ZZC020002("无桌自助餐主界面-无桌自助餐-开单页-开单页结账按钮"),
    ZZC020003("无桌自助餐主界面-无桌自助餐-开单页-开单页结账按钮"),
    ZZC020004("无桌自助餐主界面-无桌自助餐-开单页-开单页修改服务员"),
    ZZC020005("无桌自助餐主界面-无桌自助餐-开单页-开单页修改销售员"),
    ZZC020006("无桌自助餐主界面-无桌自助餐-订单列表-无桌自助餐列表结账按钮"),
    ZZC020007("无桌自助餐主界面-无桌自助餐-订单列表-无桌自助餐列表改单按钮"),
    ZZC020008("无桌自助餐主界面-无桌自助餐-订单列表-无桌自助餐列表退押金按钮"),
    ZZC030001("自助餐点单页面-自助餐点单页面-确定"),
    ZZC030002("自助餐点单页面-自助餐点单页面-修改服务员"),
    ZZC030003("自助餐点单页面-自助餐点单页面-修改销售员"),
    ZZC030004("自助餐点单页面-自助餐点单页面-更改餐标"),

    /***团餐餐代码定义***/
    TC010001("团餐主界面-团餐主界面-创建团餐预定"),
    TC010002("团餐主界面-团餐主界面-底部开台"),
    TC010003("团餐主界面-团餐主界面-管理团餐模板"),
    TC010004("团餐主界面-团餐主界面-日历"),
    TC010005("团餐主界面-团餐主界面-今天按钮"),
    TC010006("团餐主界面-团餐主界面-单据中开台"),
    TC010007("团餐主界面-团餐主界面-单据中改单"),
    TC010008("团餐主界面-团餐主界面-单据中结账"),
    TC020001("管理团餐模板-管理团餐模板-顶部删除模板"),
    TC020002("管理团餐模板-管理团餐模板-搜索"),
    TC020003("管理团餐模板-管理团餐模板-创建新模板"),
    TC020004("管理团餐模板-管理团餐模板-删除模板"),
    TC020005("管理团餐模板-管理团餐模板-查看模板"),
    TC020006("管理团餐模板-管理团餐模板-返回"),
    TC020007("管理团餐模板-管理团餐模板-搜索删除"),
    TC030001("团餐点菜-团餐点菜-菜单预览"),
    TC030002("团餐点菜-团餐点菜-修改桌按钮"),
    TC030003("团餐点菜-团餐点菜-菜品团餐点"),
    TC030004("团餐点菜-团餐点菜-菜品单点"),

    /***排队代码定义***/
    PD010001("侧边栏-侧边栏-排队"),
    PD010002("侧边栏-侧边栏-播放列表"),
    PD010003("侧边栏-侧边栏-自助排队"),
    PD010004("侧边栏-侧边栏-排队报表中心"),
    PD020001("主界面-导航栏-排队列表"),
    PD020002("主界面-导航栏-历史记录"),
    PD020003("主界面-导航栏-创建排队"),
    PD020004("主界面-导航栏-空闲桌台"),
    PD020005("主界面-导航栏-创建排队"),
    PD020006("主界面-创建排队-取号"),
    PD020007("主界面-排队列表-叫号"),
    PD020008("主界面-排队列表-入场"),
    PD020009("主界面-排队列表-过号"),
    PD020010("主界面-历史记录-入场"),
    PD020011("主界面-入场-直接入场"),
    PD020012("主界面-入场-入场并开台"),
    PD030001("自助排队--取号"),

    /***预订代码定义***/
    YD010001("主界面-导航栏-列表打印"),
    YD010002("主界面-导航栏-选择日期"),
    YD010003("主界面-导航栏-搜索"),
    YD010004("主界面-导航栏-详情打印"),
    YD010005("主界面-预订列表-创建预订"),
    YD020001("创建/编辑预订-预订信息-选择桌台"),
    YD020002("创建/编辑预订-预订信息-预点菜"),
    YD020003("创建/编辑预订-预订信息-更多信息录入"),
    YD020004("创建/编辑预订-预订信息-预订完成"),
    YD030001("主界面-预订详情-短信"),
    YD030002("主界面-预订详情-电话"),
    YD030003("主界面-预订详情-编辑"),
    YD030004("主界面-预订详情-预订信息TAB"),
    YD030005("主界面-预订详情-当前状态TAB"),
    YD030006("主界面-预订详情-取消预订"),
    YD030007("主界面-预订详情-顾客到店"),
    YD030008("主界面-顾客到店-直接到店"),
    YD030009("主界面-顾客到店-到店并开台"),
    YD030010("主界面-预订列表-创建团餐预订"),
    YD030011("主界面-预订详情-接受"),
    YD030012("主界面-预订详情-离店"),
    YD040001("创建/编辑团餐预订-预订信息-选择餐标"),
    YD040002("创建/编辑团餐预订-预订信息-确定"),
    YD040003("创建/编辑团餐预订-点单-菜品选择-团餐"),
    YD040004("创建/编辑团餐预订-点单-菜单选择-单点"),
    YD040005("创建/编辑团餐预订-点单-确认"),

    /***顾客代码定义***/
    GK010001("主界面-会员列表-顾客搜索"),
    GK010002("主界面-会员列表-新建会员"),
    GK010003("主界面-会员列表-点选会员"),
    GK010004("主界面-会员详情-点击人脸认证/更改人脸"),
    GK010005("主界面-会员详情-点击实体卡绑定"),
    GK010006("主界面-会员详情-点击会员升级"),
    GK010007("主界面-会员详情-点击电话"),
    GK010008("主界面-会员详情-点击会员编辑"),
    GK010009("主界面-会员详情-点击基本信息"),
    GK010010("主界面-会员详情-点击实体卡信息"),
    GK010011("主界面-会员基本信息-余额"),
    GK010012("主界面-会员基本信息-可用积分"),
    GK010013("主界面-会员基本信息-可用券"),
    GK010014("主界面-会员基本信息-修改密码"),
    GK010015("主界面-会员基本信息-账户充值"),
    GK010016("主界面-会员基本信息-创建订单"),
    GK010017("主界面-实体卡信息-实体卡余额"),
    GK010018("主界面-实体卡信息-可用积分"),
    GK010019("主界面-实体卡信息-实体卡充值"),
    GK010020("主界面-账户充值-关闭"),
    GK010021("主界面-账户充值-满赠规则"),
    GK010022("主界面-账户充值-推销员"),
    GK010023("主界面-账户充值-充值支付"),
    GK010024("主界面-创建订单-创建外卖"),
    GK010025("主界面-创建订单-创建排队"),
    GK010026("主界面-创建订单-创建预定"),
    GK010027("主界面-修改密码-获取验证码"),
    GK010028("主界面-修改密码-下一步"),
    GK010029("主界面-修改密码-保存"),
    GK020001("创建/编辑会员-添加会员-分组"),
    GK020002("创建/编辑会员-添加会员-生日"),
    GK020003("创建/编辑会员-添加会员-号码所属地"),
    GK020004("创建/编辑会员-添加会员-保存"),
    GK020005("创建/编辑会员-编辑会员-虚拟卡储值"),
    GK020006("创建/编辑会员-编辑会员-实体卡绑定"),
    GK020007("创建/编辑会员-编辑会员-保存"),
    GK020008("创建/编辑会员-保存成功弹窗-返回列表"),
    GK020009("创建/编辑会员-保存成功弹窗-虚拟卡储值"),
    GK020010("创建/编辑会员-保存成功弹窗-实体卡绑定"),
    GK030001("侧边栏-侧边栏-顾客"),
    GK030002("侧边栏-侧边栏-实体卡管理"),
    GK030003("侧边栏-侧边栏-单据列表"),
    GK040001("实体卡管理-售卡-匿名卡"),
    GK040002("实体卡管理-售卡-会员卡"),
    GK040003("实体卡管理-退卡-匿名卡"),
    GK040004("实体卡管理-退卡-会员卡"),
    GK050001("会员卡售卡-实体卡列表-全选"),
    GK050002("会员卡售卡-实体卡列表-清空"),
    GK050003("会员卡售卡-实体卡列表-会员卡选择"),
    GK050004("会员卡售卡-实体卡列表-选择推销员"),
    GK050005("会员卡售卡-实体卡列表-售卡"),
    GK050006("会员卡售卡-实体卡列表-激活"),
    GK050007("会员卡售卡-实体卡列表-充值"),
    GK050008("会员卡售卡-激活-查找会员"),
    GK050009("会员卡售卡-激活-直接绑定"),
    GK050010("会员卡售卡-激活-完善资料并绑定"),
    GK050011("会员卡售卡-刷卡操作-会员卡录入"),
    GK050012("会员卡退卡-会员卡退卡-退卡"),
    GK050013("会员卡退卡-刷卡-录入");

    private String desc;

    UserActionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}