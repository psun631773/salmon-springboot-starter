/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.salmon.ca.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.salmon.ca.properties.SalmonEsbRocketMQProperties;
import com.salmon.ca.redischeck.RedisCheckService;
import com.salmon.ca.util.SalmonLogUtil;
import com.salmon.ca.enums.SalmonMessageType;
import com.salmon.ca.imp.SalmonRocketMQListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;

import static com.salmon.ca.singleton.SalmonMapperSingleton.objectMapper;
/**
 * @Author sun chongwen
 * @Date 2023-09-17 15:39
 * @Version 1.0
 */
@Slf4j
public class SalmonEsbRocketMQConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener, SalmonRocketMQListener<String> {
    private static final Logger logger = LogManager.getLogger(SalmonEsbRocketMQConsumer.class);

    @Autowired
    private SalmonEsbRocketMQProperties properties;
    @Autowired
    private  RedisCheckService redisCheckService;



    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {


//        // 设置最大重试次数
//        defaultMQPushConsumer.setMaxReconsumeTimes(1);
//        // 顺序消息消费失败的消费重试时间间隔，单位毫秒，默认为1000，其取值范围为[10, 30000]
//        defaultMQPushConsumer.setSuspendCurrentQueueTimeMillis(20000);
//        // 设置最小线程数 设置防止崩溃。
//        defaultMQPushConsumer.setConsumeThreadMin(1);
//        defaultMQPushConsumer.setConsumeThreadMax(2);
        defaultMQPushConsumer.setMaxReconsumeTimes(properties.getMaxReconsumeTimes());
        defaultMQPushConsumer.setSuspendCurrentQueueTimeMillis(properties.getSuspendCurrentQueueTimeMillis());
        defaultMQPushConsumer.setConsumeThreadMin(properties.getConsumeThreadMin());
        defaultMQPushConsumer.setConsumeThreadMax(properties.getConsumeThreadMax());

    }


    @Override
    public void onMessage(MessageExt messageExt) {


        String id = "";
        String timestamp = "";
        String schema = "";
        String source = "";
        String type = "";
        String data = "";
        String yuJiaMessageJson = new String(messageExt.getBody());
        TypeFactory typeFactory = objectMapper.getTypeFactory();

        try {
            JsonNode jsonNode = objectMapper.readTree(yuJiaMessageJson);
            id = getStringValue(jsonNode, "id", "");
            schema = getStringValue(jsonNode, "schema", "");
            source = getStringValue(jsonNode, "source", "");
            type = getStringValue(jsonNode, "type", "");

        } catch (Exception e) {
            log.error(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_REC.getDescription(), SalmonMessageType.YUJIA_MSG_REC, "F", new String(messageExt.getBody()), schema, source, "电文解析阶段", "接收消息失败 电文不满足json结构"));
            throw new RuntimeException(e);
        }
        /*
         消息是否被消费过。消费过抛异常消息直接到死心队列。
         */
        String messageId = messageExt.getMsgId();

        if (redisCheckService.isMessageIdConsumed(messageId)){
            log.info(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_REC.getDescription(), SalmonMessageType.YUJIA_MSG_REC, "F", new String(messageExt.getBody()), schema, source, "电文解析阶段", "redis进行Message-key校验，重复消息，不予以处理"));
            throw new RuntimeException(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_REC.getDescription(), SalmonMessageType.YUJIA_MSG_REC, "F", new String(messageExt.getBody()), schema, source, "电文解析阶段", "redis进行Message-key校验，重复消息，不予以处理"));
        }else{
            redisCheckService.saveMessageId(messageId);
        }
        yuJiaMessageHandle(yuJiaMessageJson);

        log.info(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_REC.getDescription(), SalmonMessageType.YUJIA_MSG_REC, "S", new String(messageExt.getBody()), schema, source, "电文解析阶段", "接收消息成功"));

    }

    private static String getStringValue(JsonNode jsonNode, String fieldName, String defaultValue) {
        JsonNode fieldNode = jsonNode.get(fieldName);
        return (fieldNode != null && fieldNode.isTextual()) ? fieldNode.asText() : defaultValue;
    }


    public void yuJiaMessageHandle(String messageJson) {

    }
}