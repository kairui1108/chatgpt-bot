package com.ruikai.chat.api.test;

import com.alibaba.fastjson.JSON;
import com.ruikai.chat.api.domain.zsxq.IZsxqApi;
import com.ruikai.chat.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ruikai
 * @version 1.0
 * @date 2023/4/1 20:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ZsxqTest {

    @Value("${chatgpt-bot.groupId}")
    private String groupId;

    @Value("@{chatgpt-bot.cookie}")
    private String cookie;

    @Autowired
    private IZsxqApi zsxqApi;

    @Test
    public void test_zsxq() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopic(groupId, cookie);
        log.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
    }
}
