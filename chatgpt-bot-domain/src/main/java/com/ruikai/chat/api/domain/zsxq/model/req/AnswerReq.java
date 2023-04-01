package com.ruikai.chat.api.domain.zsxq.model.req;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/4/1 19:42
 */
public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }

}
