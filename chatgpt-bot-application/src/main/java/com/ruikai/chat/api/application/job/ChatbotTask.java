package com.ruikai.chat.api.application.job;


import com.alibaba.fastjson.JSON;
import com.ruikai.chat.api.domain.openai.IOpenAI;
import com.ruikai.chat.api.domain.zsxq.IZsxqApi;
import com.ruikai.chat.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.ruikai.chat.api.domain.zsxq.model.vo.Topics;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/4/1 19:42
 */
@Slf4j
public class ChatbotTask implements Runnable {


    private String groupName;
    private String groupId;
    private String cookie;
    private String openAiKey;
    private boolean silenced;

    private IZsxqApi zsxqApi;
    private IOpenAI openAI;

    public ChatbotTask(String groupName, String groupId, String cookie, String openAiKey, IZsxqApi zsxqApi, IOpenAI openAI, boolean silenced) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.cookie = cookie;
        this.openAiKey = openAiKey;
        this.zsxqApi = zsxqApi;
        this.openAI = openAI;
        this.silenced = silenced;
    }

    @Override
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                log.info("{} 随机打烊中...", groupName);
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                log.info("{} 打烊时间不工作，AI 下班了！", groupName);
                return;
            }

            // 1. 检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopic(groupId, cookie);
            log.info("{} 检索结果：{}", groupName, JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                log.info("{} 本次检索未查询到待会答问题", groupName);
                return;
            }

            // 2. AI 回答
            Topics topic = topics.get(topics.size() - 1);
            String answer = openAI.doChatGPT(openAiKey, topic.getQuestion().getText().trim());
            // 3. 问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, silenced);
            log.info("{} 编号：{} 问题：{} 回答：{} 状态：{}", groupName, topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (Exception e) {
            log.error("{} 自动回答问题异常", groupName, e);
        }
    }

}
