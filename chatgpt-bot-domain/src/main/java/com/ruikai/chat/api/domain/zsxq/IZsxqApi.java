package com.ruikai.chat.api.domain.zsxq;

import com.ruikai.chat.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/4/1 19:42
 */
public interface IZsxqApi {

    /**
     * 查寻未回答题目
     * @param groupId
     * @param cookie
     * @throws IOException
     * @return
     */
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopic(String groupId, String cookie) throws IOException;

    /**
     * 回答
     * @param groupId
     * @param cookie
     * @param topicId
     * @param text
     * @param silenced
     * @return boolean
     * @throws IOException
     */
    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
