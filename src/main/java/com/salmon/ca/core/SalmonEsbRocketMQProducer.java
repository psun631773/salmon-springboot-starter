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


import com.salmon.ca.enums.SalmonMessageType;
import com.salmon.ca.util.SalmonLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;


import static com.salmon.ca.constant.SalmonReturnConstant.DO_FLAG_FAIL;
import static com.salmon.ca.constant.SalmonReturnConstant.DO_FLAG_SUCCESS;
import static com.salmon.ca.util.SalmonMsgUtil.msg2YuJiaJson;
import static org.apache.rocketmq.client.producer.SendStatus.SEND_OK;
/**
 * @Author sun chongwen
 * @Date 2023-09-17 15:39
 * @Version 1.0
 */
@Slf4j
public class SalmonEsbRocketMQProducer {

    private final RocketMQTemplate rocketMQTemplate;


    public SalmonEsbRocketMQProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }


    /**
     * 同步发送消息
     *
     * @param messageObject 要发送的消息对象
     * @param topic         消息主题
     * @param tag           消息标签
     * @return 返回执行结果，成功返回DO_FLAG_SUCCESS，失败返回DO_FLAG_FAIL
     */
    public <T> int sendSyncMsg(T messageObject, String topic, String tag) {
        String yuJiaInfoJson = msg2YuJiaJson(messageObject, topic, tag);
        SendResult sendResult = rocketMQTemplate.syncSend(topic + ":" + tag, yuJiaInfoJson, 10000);
        if (sendResult.getSendStatus() == SEND_OK) {
            log.info(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_SND.getDescription(), SalmonMessageType.YUJIA_MSG_SND, "S", yuJiaInfoJson, topic, tag, "SND", "发送成功"));
            return DO_FLAG_SUCCESS;
        } else {
            log.error(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_SND.getDescription(), SalmonMessageType.YUJIA_MSG_SND, "F", yuJiaInfoJson, topic, tag, "SND", "发送失败"));
            return DO_FLAG_FAIL;
        }
    }

    /**
     * 异步发送消息（无返回）
     *
     * @param messageObject 要发送的消息对象
     * @param topic         消息主题
     * @param tag           消息标签
     */
    public <T> void sendMsgNoReturn(T messageObject, String topic, String tag) {
        String yuJiaInfoJson = msg2YuJiaJson(messageObject, topic, tag);
        rocketMQTemplate.sendOneWay(topic + ":" + tag, yuJiaInfoJson);

    }
    /**
     * 同步发送顺序消息
     *
     * @param messageObject   消息内容对象
     * @param topic           消息主题
     * @param tag             消息标签
     * @param messageSequeue  消息队列标识，用于指定消息发送至同一个队列的关键字
     *                        注意: 应确保相关消息都使用相同的 messageSequeue 才能保证有序性
     * @param <T>             消息内容对象的类型
     */
    public <T> int syncSendOrderly(T messageObject, String topic, String tag ,String messageSequeue) {
        String yuJiaInfoJson = msg2YuJiaJson(messageObject, topic, tag);
        String id = getId(yuJiaInfoJson);
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(topic + ":" + tag, yuJiaInfoJson, Integer.toString(messageSequeue.hashCode()));// 这个messkey表示消息发送至同一个队列
        if (sendResult.getSendStatus() == SEND_OK) {
            log.info(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_SND.getDescription(), SalmonMessageType.YUJIA_MSG_SND, "S", yuJiaInfoJson, topic, tag, "SND", "发送成功"));
            return DO_FLAG_SUCCESS;
        } else {
            log.error(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_SND.getDescription(), SalmonMessageType.YUJIA_MSG_SND, "F", yuJiaInfoJson, topic, tag, "SND", "发送失败"));
            return DO_FLAG_FAIL;
        }
    }
    /**
     * 发送延时消息
     *
     * @param messageObject 要发送的消息对象
     * @param topic         消息主题
     * @param tag           消息标签
     * @param delayLevel    延时级别，表示延时的时间，具体值需根据RocketMQ的延时级别配置
     *                      可参考RocketMQ文档：https://rocketmq.apache.org/docs/delay-example/
     *                      例如，延时10秒可以设置为"3"
     *                      若 delayLevel 不合法，应抛出异常或进行适当处理
     *                      延迟级别只能设置1,2,3,4 代表1秒 5秒 10秒 30秒
     */
    public <T> int sendDelayedMessage(T messageObject, String topic, String tag, String delayLevel) {
        deLayLevelCheck(delayLevel);
        String yuJiaInfoJson = msg2YuJiaJson(messageObject, topic, tag);
        Message<String> rocketMessage = MessageBuilder.withPayload(yuJiaInfoJson).build();
        // 发送消息
//        rocketMQTemplate.send(topic + ":" + tag, rocketMessage);
        if (delayLevel.equals(null)) {
            delayLevel = "3";
        }

        int delay= Integer.parseInt(delayLevel);
        SendResult sendResult = rocketMQTemplate.syncSend(topic + ":" + tag, rocketMessage, 3000, delay);
        if (sendResult.getSendStatus() == SEND_OK) {
            log.info(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_SND.getDescription(), SalmonMessageType.YUJIA_MSG_SND, "S", yuJiaInfoJson, topic, tag, "SND", "发送延迟消息成功"));
            return DO_FLAG_SUCCESS;
        } else {
            log.error(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_SND.getDescription(), SalmonMessageType.YUJIA_MSG_SND, "F", yuJiaInfoJson, topic, tag, "SND", "发送延迟消息失败"));
            return DO_FLAG_FAIL;
        }


    }


    /**
     * 检查延时级别是否合法
     *
     * @param delayLevel 延时级别
     * @throws RuntimeException 如果延时级别不合法
     */
    public void deLayLevelCheck(String delayLevel) {
        if (!("1".equals(delayLevel) || "2".equals(delayLevel) || "3".equals(delayLevel) || "4".equals(delayLevel))) {
            throw new RuntimeException("延迟级别只能设置1,2,3,4 代表1秒 5秒 10秒 30秒");
        }
    }

    public String getId(String jsonString){
        String[] parts = jsonString.split("\"id\":\"");
        if (parts.length > 1) {
            String idPart = parts[1];
            String idValue = idPart.substring(0, idPart.indexOf("\""));
            log.info("id的对应值为: " + idValue);
            return idValue;
            // 打印结果

        } else {
            log.info("找不到'id'字段");
            return "";
        }
    }
}
