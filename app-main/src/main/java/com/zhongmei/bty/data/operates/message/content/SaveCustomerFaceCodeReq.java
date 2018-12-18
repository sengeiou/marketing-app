package com.zhongmei.bty.data.operates.message.content;

import com.zhongmei.bty.commonmodule.data.operate.message.BaseRequest;

/**
 * Created by demo on 2018/12/15
 */

public class SaveCustomerFaceCodeReq extends BaseRequest {

    private Long userId;

    private Long customerId;

    private String faceCode;

    private String peopleId;

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setFaceCode(String faceCode) {
        this.faceCode = faceCode;
    }
}