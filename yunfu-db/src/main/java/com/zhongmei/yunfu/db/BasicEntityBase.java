package com.zhongmei.yunfu.db;

import com.j256.ormlite.field.DatabaseField;
import com.zhongmei.yunfu.util.ValueEnums;
import com.zhongmei.yunfu.db.enums.StatusFlag;

/**
 *

 *
 */
public class BasicEntityBase extends IdEntityBase {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public interface $ extends IdEntityBase.$ {

        /**
         * status_flag
         */
        String statusFlag = "status_flag";

        /**
         * server_create_time
         */
        String serverCreateTime = "server_create_time";

        /**
         * server_update_time
         */
        String serverUpdateTime = "server_update_time";

        /**
         * brand_identy
         */
        String brandIdenty = "brand_identy";

        /**
         * 门店id
         */
        String shopIdenty = "shop_identy";

    }

    /**
     * 状态
     */
    @DatabaseField(columnName = "status_flag", canBeNull = false)
    protected Integer statusFlag;

    /**
     * 服务器创建时间
     */
    @DatabaseField(columnName = "server_create_time")
    private Long serverCreateTime;

    /**
     * 服务器最后修改时间
     */
    @DatabaseField(columnName = "server_update_time")
    private Long serverUpdateTime;

    /**
     * 品牌Identy
     */
    @DatabaseField(columnName = "brand_identy", canBeNull = false)
    private Long brandIdenty;

    /**
     * 品牌Identy
     */
    @DatabaseField(columnName = "shop_identy", canBeNull = false)
    private Long shopIdenty;

    public Long getServerCreateTime() {
        return serverCreateTime;
    }

    public void setServerCreateTime(Long serverCreateTime) {
        this.serverCreateTime = serverCreateTime;
    }

    public Long getServerUpdateTime() {
        return serverUpdateTime;
    }

    public void setServerUpdateTime(Long serverUpdateTime) {
        this.serverUpdateTime = serverUpdateTime;
    }

    public StatusFlag getStatusFlag() {
        return ValueEnums.toEnum(StatusFlag.class, statusFlag);
    }

    public void setStatusFlag(StatusFlag statusFlag) {
        this.statusFlag = ValueEnums.toValue(statusFlag);
    }

    public Long getBrandIdenty() {
        return brandIdenty;
    }

    public void setBrandIdenty(Long brandIdenty) {
        this.brandIdenty = brandIdenty;
    }

    public Long getShopIdenty() {
        return shopIdenty;
    }

    public void setShopIdenty(Long shopIdenty) {
        this.shopIdenty = shopIdenty;
    }

    @Override
    public Long verValue() {
        return serverUpdateTime;
    }

    @Override
    public boolean isValid() {
        return ValueEnums.equalsValue(StatusFlag.VALID, statusFlag);
    }

    @Override
    public boolean checkNonNull() {
        return super.checkNonNull() && EntityBase.checkNonNull(statusFlag, brandIdenty, shopIdenty);
    }
}
