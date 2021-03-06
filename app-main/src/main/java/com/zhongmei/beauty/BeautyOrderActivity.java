package com.zhongmei.beauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zhongmei.beauty.event.EventOrderCleanRBChecked;
import com.zhongmei.beauty.events.OrderDishMaskingEvent;
import com.zhongmei.beauty.interfaces.BeautyOrderOperatorListener;
import com.zhongmei.beauty.interfaces.ITableChoice;
import com.zhongmei.beauty.order.BeautyOrderLeftFragment;
import com.zhongmei.beauty.order.BeautyOrderLeftFragment_;
import com.zhongmei.beauty.order.BeautyOrderMiddleFragment;
import com.zhongmei.beauty.order.BeautyOrderMiddleFragment_;
import com.zhongmei.beauty.order.BeautyOrderProductFragment;
import com.zhongmei.beauty.order.BeautyOrderTopFragment;
import com.zhongmei.beauty.order.BeautySearchFragment;
import com.zhongmei.beauty.order.BeautySearchFragment_;
import com.zhongmei.beauty.order.BeautySetmealFragment;
import com.zhongmei.beauty.order.BeautySetmealFragment_;
import com.zhongmei.beauty.order.event.BeautyOrderCustomerEvent;
import com.zhongmei.beauty.order.util.IChangeMiddlePageListener;
import com.zhongmei.beauty.widgets.BeautyTablePopWindow;
import com.zhongmei.bty.base.MainBaseActivity;
import com.zhongmei.bty.basemodule.customer.manager.CustomerManager;
import com.zhongmei.bty.basemodule.orderdish.bean.DishDataItem;
import com.zhongmei.bty.basemodule.orderdish.bean.IShopcartItemBase;
import com.zhongmei.bty.basemodule.orderdish.bean.ShopcartItem;
import com.zhongmei.bty.basemodule.orderdish.enums.ItemType;
import com.zhongmei.bty.basemodule.shopmanager.interfaces.ChangePageListener;
import com.zhongmei.bty.basemodule.shoppingcart.DinnerShoppingCart;
import com.zhongmei.bty.basemodule.trade.event.ActionCloseOrderDishActivity;
import com.zhongmei.bty.basemodule.trade.manager.DinnerCashManager;
import com.zhongmei.bty.basemodule.trade.manager.DinnerShopManager;
import com.zhongmei.bty.common.view.LoadingView;
import com.zhongmei.bty.commonmodule.database.enums.CardRechagingStatus;
import com.zhongmei.bty.dinner.cash.DinnerDishCustomerLogin;
import com.zhongmei.bty.dinner.cash.DinnerDishCustomerLogin_;
import com.zhongmei.bty.dinner.orderdish.DinnerDishCommentFragment;
import com.zhongmei.bty.dinner.orderdish.DishLeftFragment;
import com.zhongmei.bty.dinner.vo.LoadingFinish;
import com.zhongmei.bty.mobilepay.event.ActionClose;
import com.zhongmei.bty.snack.event.EventEditModle;
import com.zhongmei.yunfu.Constant;
import com.zhongmei.yunfu.R;
import com.zhongmei.yunfu.context.util.Utils;
import com.zhongmei.yunfu.db.entity.trade.Tables;
import com.zhongmei.yunfu.db.entity.trade.TradeTable;
import com.zhongmei.yunfu.db.enums.BusinessType;
import com.zhongmei.yunfu.util.ToastUtil;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 美业点单界面
 * Created by demo on 2018/12/15
 */
public class BeautyOrderActivity extends MainBaseActivity implements View.OnClickListener, BeautyOrderOperatorListener, ITableChoice, PopupWindow.OnDismissListener {

    private final static String TAG = BeautyOrderActivity.class.getSimpleName();

    // 默认进入结算界面
    public static final String IS_DEFAULT_SETTLE = "isDefaultSettlePage";

    protected View shadowView;// 遮罩层
    protected View viewMasking;
    protected LinearLayout orderDishMiddle;
    protected ImageButton btn_close;

    private FragmentManager mFragmentManager;
//    private BeautyOrderTopFragment mTopFragment;

    protected BeautyOrderLeftFragment mLeftFragment;

    private BeautyOrderProductFragment mDishHomePageFragment;

    private BeautySearchFragment mDinnerDishSearchFragment;// 菜品搜索界面

    private BeautySetmealFragment mDinnerDishSetmealFragment;// 菜品子菜列表界面

    private DinnerDishCommentFragment mDinnerDishCommentFragment;// 整单备注界面

    private DinnerDishCustomerLogin mCustomerLogin;// 会员登录界面

    //中间操作栏
    private BeautyOrderMiddleFragment middleFragment;

    private int SHOWINDEX;//当前加载Fragment编号

    private String lastDishUUID = "";

    private DinnerShoppingCart mShoppingCart;


    public LoadingView leftLoading;
    public LoadingView rightLoading;
    //上一次中间条默认显示页
    private int lastMiddlePage = IChangeMiddlePageListener.DEFAULT_PAGE;


    private BeautyTablePopWindow tablePopuwindow;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(getLayoutRes());
        init();
        Intent intent = getIntent();
        registerEventBus();
    }

    private int getLayoutRes() {
        return R.layout.beauty_order_layout;
    }

    public void init() {
        shadowView = findViewById(R.id.view_shadow);
        viewMasking = findViewById(R.id.view_masking);
        orderDishMiddle = (LinearLayout) findViewById(R.id.orderDishMiddle);
        btn_close = (ImageButton) findViewById(R.id.btn_close);
        leftLoading = (LoadingView) findViewById(R.id.leftLoading);
        rightLoading = (LoadingView) findViewById(R.id.rightLoading);
        btn_close.setOnClickListener(this);
        shadowView.setOnClickListener(this);
        shadowView.setAlpha(0.8f);
        mFragmentManager = getSupportFragmentManager();
//        mTopFragment = (BeautyOrderTopFragment) mFragmentManager.findFragmentById(R.id.beauty_order_top_fragment);
//        mTopFragment.setOperatorListener(this);
        buildView();

        tablePopuwindow = new BeautyTablePopWindow(this, BusinessType.BEAUTY);
        tablePopuwindow.setiTableChoiceListener(this);
        tablePopuwindow.setOnDismissListener(this);

        orderDishMiddle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }


    /**
     * 收银结束关闭订单
     */
    public void onEventMainThread(ActionCloseOrderDishActivity event) {
        this.finish();
        if(mLeftFragment!=null){
            mLeftFragment.clearShopCart();
        }
    }

    /**
     * 显示中间的操作
     */
    public void onEventMainThread(DishDataItem item) {
        showMiddleFragment(item);
    }


    public void onEventMainThread(ActionClose closeMiddView) {
        doClose();
    }

    public void onEventMainThread(OrderDishMaskingEvent event) {
        if (event.isShowMasking()) {
            viewMasking.setVisibility(View.VISIBLE);
        } else {
            viewMasking.setVisibility(View.GONE);
        }
    }

    public void onEventMainThread(BeautyOrderCustomerEvent event) {
        if (event.mEventFlag == BeautyOrderCustomerEvent.EventFlag.LOGIN) {
            middleFragment.doLoginCustomer(event.mCustomerNew);
//            mTopFragment.doLoginCustomer();
            new DinnerCashManager().updateIntegralCash(event.mCustomerNew);
            if (event.mCustomerNew.card == null || event.mCustomerNew.card != null && event.mCustomerNew.card.getRightStatus() == CardRechagingStatus.EFFECTIVE) {
                DinnerShopManager.getInstance().getShoppingCart().memberPrivilege(true, true);//设置会员折扣／会员价
            }
        } else {
            middleFragment.doExitCustomer();
            doClose();//关闭浮层
//            mTopFragment.doLoginOutCustomer();
        }
    }

    public void buildView() {
        buildFragment();
    }


    /**
     * 加载界面中购物车Fragment和点菜界面Fragment
     */
    public void buildFragment() {
        mShoppingCart = DinnerShoppingCart.getInstance();
        mShoppingCart.unRegisterListener();

        mLeftFragment = new BeautyOrderLeftFragment_();
        mLeftFragment.registerLoadingListener(mLeftLoadingFinish);
        mLeftFragment.registerListener(mChangePageListener, middleChangeListener);

        mDishHomePageFragment = new BeautyOrderProductFragment();
        addFragment(R.id.orderDishLeftView, mLeftFragment, DishLeftFragment.class.getName(), false);

        mDishHomePageFragment.registerListener(mChangePageListener);

        mDishHomePageFragment.registerLoadingListener(mRightLoadingFinish);

        SHOWINDEX = ChangePageListener.ORDERDISHLIST;
        replaceFragment(R.id.orderDishRightView, mDishHomePageFragment, mDishHomePageFragment.getClass().getName(), false);

        initMiddleFragment();
    }

    IChangeMiddlePageListener middleChangeListener = new IChangeMiddlePageListener() {
        @Override
        public void changePage(int currentPage, String uuid) {
            changeMidddlePage(currentPage, uuid);
        }

        @Override
        public void closePage(IShopcartItemBase item) {
            doClose();
            if (item != null) {
                mLeftFragment.doSelected(item);
            } else {
                mLeftFragment.clearAllSelected();
            }
        }

        @Override
        public void showCombo(ShopcartItem item) {
            if (item != null) {
                mLeftFragment.doSelected(item);
            }
            if (middleFragment != null) {
                middleFragment.hideContent();
            }
            shadowView.setVisibility(View.GONE);
            btn_close.setVisibility(View.GONE);
        }

        @Override
        public void showCloseView(boolean isShow) {
            btn_close.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    };

    /**
     * 中间条点击控制左侧购物车
     *
     * @param page
     */
    private void changeMidddlePage(int page, String uuid) {
        shadowView.setVisibility(View.VISIBLE);
        btn_close.setVisibility(View.VISIBLE);
        switch (page) {
            case IChangeMiddlePageListener.DEFAULT_PAGE:
                mLeftFragment.doSelected(uuid);
                break;
            case IChangeMiddlePageListener.OTHER_PAGE:
//                mLeftFragment.goDefaultDiscountMode();
                break;
            case IChangeMiddlePageListener.DEFINE_DISCOUNT_PAGE:
                mLeftFragment.goAllDiscountMode();
                break;
            case IChangeMiddlePageListener.BATCH_DISCOUNT_PAGE:
                mLeftFragment.goBatchDiscountMode();
                break;
            case IChangeMiddlePageListener.COMMON_DEFINE_PAGE:
                mLeftFragment.clearAllSelected();
                break;
        }
        if (lastMiddlePage == IChangeMiddlePageListener.DEFINE_DISCOUNT_PAGE || lastMiddlePage == IChangeMiddlePageListener.BATCH_DISCOUNT_PAGE) {
            mLeftFragment.goDefaultDiscountMode();
        } else if (lastMiddlePage == IChangeMiddlePageListener.MARKET_ACTIVITY_PAGE && page != IChangeMiddlePageListener.MARKET_ACTIVITY_PAGE) {
            mLeftFragment.cancelMarketPage();
        }
        lastMiddlePage = page;
    }


    private void showMiddleFragment(DishDataItem item) {
        shadowView.setVisibility(View.VISIBLE);
        btn_close.setVisibility(View.VISIBLE);
        onClearSelected();
        if (item.getType() == ItemType.COMBO) {
            middleFragment.doCancel();
            shadowView.setVisibility(View.GONE);
            btn_close.setVisibility(View.GONE);
            if (SHOWINDEX != ChangePageListener.DISHCOMBO) {
                middleFragment.doSelect(item);
            }
        } else {
            middleFragment.doSelect(item);
            middleFragment.showContent();
        }
    }

    private void initMiddleFragment() {
        middleFragment = new BeautyOrderMiddleFragment_();
        middleFragment.setOperatorListener(this);
        replaceFragment(R.id.orderDishMiddle, middleFragment, middleFragment.getClass().getName(), false);
        middleFragment.registerListener(mChangePageListener, middleChangeListener);
    }

    public LoadingFinish mLeftLoadingFinish = new LoadingFinish() {
        @Override
        public void loadingFinish() {
            leftLoading.setVisibility(View.GONE);
        }
    };

    public LoadingFinish mRightLoadingFinish = new LoadingFinish() {
        @Override
        public void loadingFinish() {
            rightLoading.setVisibility(View.GONE);
        }
    };


    public ChangePageListener mChangePageListener = new ChangePageListener() {

        @Override
        public void changePage(int pageNo, Bundle bundle) {
            Boolean noNeedCheck = bundle != null ? bundle.getBoolean(Constant.NONEEDCHECK) : true;
            String itemUUid = bundle != null ? bundle.getString(Constant.EXTRA_SHOPCART_ITEM_UUID) : "";
            if (itemUUid == null) {
                itemUUid = "";
            }
            if (!noNeedCheck) {
                // 切换界面时验证当前套餐子菜选择是否满足规则

                ShopcartItem mShopcartItem = mShoppingCart.getDinnerShopcartItem(itemUUid);

                if (!mShoppingCart.checkDishIsVaild(mShoppingCart.getShoppingCartVo(), mShopcartItem)) {
                    mShoppingCart.showCheckDialog(mShoppingCart.getShoppingCartVo(),
                            pageNo,
                            mChangePageListener,
                            getSupportFragmentManager(),
                            mShopcartItem,
                            null);
                    return;
                }
            }
            // 如果是当前界面并且是同一个菜品或子菜则不切换界面
            if (SHOWINDEX == pageNo && itemUUid.equals(lastDishUUID)) {
                return;
            }

            switch (pageNo) {
                case ChangePageListener.ORDERDISHLIST:
                    showDishListFragment(mDishHomePageFragment);
                    break;

                case ChangePageListener.SEARCH:
                    // 设置为编辑状态
                    EventBus.getDefault().post(new EventEditModle(true));
                    mDinnerDishSearchFragment = new BeautySearchFragment_();
                    mDinnerDishSearchFragment.registerListener(mChangePageListener);
                    changeFragment(R.id.orderDishRightView, mDinnerDishSearchFragment);
                    break;

                case ChangePageListener.DISHCOMBO:
                    // 设置为编辑状态
                    EventBus.getDefault().post(new EventEditModle(true));
                    removeFragment();
                    mDinnerDishSetmealFragment = new BeautySetmealFragment_();
                    mDinnerDishSetmealFragment.setArguments(bundle);
                    mDinnerDishSetmealFragment.registerListener(mChangePageListener);
                    changeFragment(R.id.orderDishRightView, mDinnerDishSetmealFragment);
                    DishDataItem dishDataItem = null;
                    if (mLeftFragment != null) {
                        dishDataItem = mLeftFragment.doSelected(itemUUid);
                    }
                    if (middleFragment != null && dishDataItem != null) {
//                        middleFragment.doSelect(dishDataItem);
                    }
                    break;

                case ChangePageListener.SAVE_BACK:
                    if (SHOWINDEX != ChangePageListener.ORDERDISHLIST) {
                        showDishListFragment(mDishHomePageFragment);
                        pageNo = ChangePageListener.ORDERDISHLIST;
                    } else {
                        BeautyOrderActivity.this.finish();
                    }
                    break;
                case ChangePageListener.PAGE_TABLE_HOME:
                    BeautyOrderActivity.this.finish();
                    break;

                case ChangePageListener.REMOBER_LOGIN:// 显示会员登录界面
                    mCustomerLogin = new DinnerDishCustomerLogin_();
                    mCustomerLogin.registerListener(mChangePageListener);
                    changeFragment(R.id.orderDishRightView, mCustomerLogin);
                    break;

                case ChangePageListener.DISH_CUSTOMER_COUPONS:// 显示会员登录信息界面
                    break;
                case ChangePageListener.PAGE_CANCEL_MARKET:
                    middleFragment.cancelMarketView();
                    // 设置为编辑状态
                    EventBus.getDefault().post(new EventEditModle(false));
                    break;
                default:
                    break;
            }
            // 通知购物车当前所处界面
            mShoppingCart.setIndexPage(pageNo);
            SHOWINDEX = pageNo;
            lastDishUUID = itemUUid;
        }

        @Override
        public void clearShoppingCart() {
            // TODO Auto-generated method stub
            shadowView.setVisibility(View.GONE);
        }
    };

    /**
     * @param containerViewId
     * @param fragment
     */
    private void changeFragment(int containerViewId, Fragment fragment) {
        if (SHOWINDEX == ChangePageListener.ORDERDISHLIST) {
            hideFragment(mDishHomePageFragment);
            addFragment(containerViewId, fragment, fragment.getClass().getName());
        } else {
            removeFragment();
            addFragment(containerViewId, fragment, fragment.getClass().getName());
        }
    }

    /**
     * @Title: showDishList
     * @Description: 展示碎片
     * @Param @param mFragment TODO
     * @Return void 返回类型
     */
    private void showDishListFragment(Fragment mFragment) {
        if (isDestroyed()) {
            return;
        }
        if (mDishHomePageFragment != null) {
            removeFragment();
            showFragment(mDishHomePageFragment, true);
        } else {
            replaceFragment(R.id.orderDishRightView, mFragment, mFragment.getClass().getName());
        }
        // 设置为非编辑状态
        EventBus.getDefault().post(new EventEditModle(false));
    }

    private void removeFragment() {
        switch (SHOWINDEX) {

            case ChangePageListener.ORDERDISHLIST:
                break;

            case ChangePageListener.SEARCH:
                removeFragment(mDinnerDishSearchFragment, mDinnerDishSearchFragment.getClass().getName());
                break;

            case ChangePageListener.DISHCOMBO:
                removeFragment(mDinnerDishSetmealFragment, mDinnerDishSetmealFragment.getClass().getName());
                break;

            case ChangePageListener.SAVE_BACK:
                break;
            case ChangePageListener.PAGE_TABLE_HOME:
                break;
            case ChangePageListener.ORDER_COMMENTS:
                removeFragment(mDinnerDishCommentFragment, mDinnerDishCommentFragment.getClass().getName());
                break;

            case ChangePageListener.REMOBER_LOGIN:
                removeFragment(mCustomerLogin, mCustomerLogin.getClass().getName());
                break;

            case ChangePageListener.DISH_CUSTOMER_COUPONS:
                break;
            default:
                break;


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public void showShadow(boolean isShow) {
        //菜品属性操作条显示时不隐藏
        if (middleFragment != null && middleFragment.isVisible()) {
            return;
        }
        shadowView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
            case R.id.view_shadow:
                doClose();
                mLeftFragment.clearAllSelected();
                break;
        }
    }

    /**
     * 关闭中间的操作条处理
     */
    protected void doClose() {
        if (middleFragment != null) {
            middleFragment.doCancel();
        }
        if (mLeftFragment != null) {
            mLeftFragment.goDefaultDiscountMode();
            mLeftFragment.cancelMarketPage();
        }
        shadowView.setVisibility(View.GONE);
        btn_close.setVisibility(View.GONE);
        onClearSelected();
        Log.e("OrderActivity", "doClose.....>");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterEventBus();
        super.onDestroy();
    }

    @Override
    public void onLoginClick() {
        if (mLeftFragment != null) {
            mLeftFragment.loginOrOut();
        }
    }

    @Override
    public void onCardClick() {
        if (middleFragment != null) {
            middleFragment.showCustomerCard();
        }
    }

    @Override
    public void onIntegralClick() {
        if (middleFragment != null) {
            middleFragment.showIntegral();
        }
    }

    @Override
    public void onPartyClick() {
        if (middleFragment != null) {
            middleFragment.showParty(mShoppingCart.getOrder());
        }
    }

    @Override
    public void onCouponClick() {
        if (middleFragment != null) {
            middleFragment.showCoupon();
        }
    }

    @Override
    public void onDiscountClick() {
        if (middleFragment != null) {
//            middleFragment.showDiscount();
        }
    }

    @Override
    public void onActivityClick() {
        if (CustomerManager.getInstance().getDinnerLoginCustomer() == null) {
            ToastUtil.showLongToast(R.string.beauty_customr_unlogin);
            onClearSelected();
            return;
        }
        if (middleFragment != null) {
            middleFragment.showMarketActivity();
        }
    }

    @Override
    public void onExtraClick() {
        if (middleFragment != null) {
            middleFragment.showExtra();
        }
    }

    @Override
    public void onRemarkClick() {
        if (middleFragment != null) {
            middleFragment.showRemark();
        }
    }

    @Override
    public void onTableClick() {
        //现实桌台信息
        showTablePopuwindow();
    }

    @Override
    public void onTableChoice(List<Tables> tables) {

    }

    @Override
    public void onClearSelected() {
//        if (mTopFragment != null) {
//            mTopFragment.doClearCheckedStatus();
//        }

        if(mLeftFragment!=null){
            mLeftFragment.clearAllSelected();
        }
    }

    private void showTablePopuwindow() {
        if (!tablePopuwindow.isShowing()) {
//            if (mTopFragment != null) {
//                Log.e("BeautyOrderActivity", "onTableChoice...onDismiss");
//                mTopFragment.setCompoundButtonStatus(BeautyOrderTopFragment.TYPE_TABLE, true);
//            }
//            tablePopuwindow.showAsDropDown(beauty_order_customer_room, DensityUtil.dip2px(context, -5f), DensityUtil.dip2px(context, -5f));
            tablePopuwindow.showAtLocation(findViewById(R.id.content_parent), Gravity.CENTER, 0, 0);
            EventBus.getDefault().post(new OrderDishMaskingEvent(true));//发送到BeautyOrderActivity显示蒙版
        }
    }


    /**
     * 设置桌台信息
     *
     * @param tradeTables
     */
    public void setTables(List<TradeTable> tradeTables) {
        if (Utils.isEmpty(tradeTables)) {
            return;
        }
        String tablesNameTmp = "";
        for (TradeTable tb : tradeTables) {
            tablesNameTmp += tb.getTableName() + ",";
        }
        String tableName = tablesNameTmp.substring(0, tablesNameTmp.length() - 1);
        if (mLeftFragment != null) {
            mLeftFragment.setTableName(tableName);
        }
        if (tablePopuwindow != null) {
            tablePopuwindow.settables(tradeTables);
        }
    }

    public void onEventMainThread(EventOrderCleanRBChecked event) {
        doClose();
    }

    @Override
    public void choiceTables(List<Tables> tables) {
        String tableTmp = "";
        if (tables != null) {
            for (Tables t : tables) {
                tableTmp += t.getTableName() + ",";
            }
            DinnerShoppingCart.getInstance().updateOrCreateTables(tables, true);
            String tableName = tableTmp.substring(0, tableTmp.length() - 1);
            if (mLeftFragment != null) {
                mLeftFragment.setTableName(tableName);
            }
        }
    }

    @Override
    public void onDismiss() {
        //设置选择桌台的按钮为不选状态；
//        if (mTopFragment != null) {
//            Log.e("BeautyOrderActivity", "onTableChoice...onDismiss");
//            mTopFragment.setCompoundButtonStatus(BeautyOrderTopFragment.TYPE_TABLE, false);
//        }
        EventBus.getDefault().post(new OrderDishMaskingEvent(false));//去掉蒙版效果，发送到BeautyOrderActivity
    }
}
