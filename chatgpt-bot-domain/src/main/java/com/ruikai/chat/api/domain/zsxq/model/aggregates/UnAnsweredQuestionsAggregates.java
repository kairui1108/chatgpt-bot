package com.ruikai.chat.api.domain.zsxq.model.aggregates;

import com.ruikai.chat.api.domain.zsxq.model.res.RespData;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/4/1 19:42
 */
public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;
    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }

}
