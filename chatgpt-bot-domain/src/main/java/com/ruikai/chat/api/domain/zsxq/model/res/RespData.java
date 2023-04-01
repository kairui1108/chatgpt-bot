package com.ruikai.chat.api.domain.zsxq.model.res;

import com.ruikai.chat.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/4/1 19:42
 */
public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

}
