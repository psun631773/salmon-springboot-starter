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
package com.salmon.ca.msgprocess;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.salmon.ca.entity.SalmonMsgHeader;
import com.salmon.ca.enums.SalmonMessageType;
import com.salmon.ca.standard.CloudEventDecoder;
import com.salmon.ca.util.SalmonLogUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.salmon.ca.singleton.SalmonMapperSingleton.objectMapper;

/**
 * @Author sun chongwen
 * @Date 2023-10-27 8:24
 * @PackageName: com.salmon.ca.msgprocess
 * @ClassName: YuJiaIMsgRecUtil
 * @Description: Utility class for processing YuJia messages.
 * Provides methods to convert YuJia messages to lists or single objects,
 * and extract the header information from a YuJia message.
 * @Version 1.0
 */
@Slf4j
public class SalmonIMsgRecUtil {

    /**
     * Converts a YuJia message to a list of objects of the specified class.
     *
     * @param msg   The YuJia message to process.
     * @param clazz The class of the objects in the list.
     * @return A list of objects parsed from the YuJia message.
     * @throws Exception If an error occurs during the conversion process.
     */
    public static <T> List<T> recYuJiaMsgToListObject(String msg, Class<?> clazz) {
        SalmonMsgHeader salmonMsgHeader = null;
        try {
            salmonMsgHeader = SalmonIMsgRecUtil.getYuJiaMsgHeader(msg);
        } catch (Exception e) {
            throw new RuntimeException(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_REC.getDescription(), SalmonMessageType.YUJIA_MSG_REC, "F", msg, "", "", "电文解析阶段", "解析消息头信息失败"));
        }
        String data = CloudEventDecoder.decodeField(msg, "data");
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return objectMapper.readValue(data, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_REC.getDescription(), SalmonMessageType.YUJIA_MSG_REC, "F", msg, salmonMsgHeader.getSource(), salmonMsgHeader.getSchema(), "电文解析阶段", "解析消息失败，消息体与接收实体类属性不匹配"));
        }
    }

    /**
     * Converts a YuJia message to a single object of the specified class.
     *
     * @param msg   The YuJia message to process.
     * @param clazz The class of the object.
     * @return The object parsed from the YuJia message.
     * @throws Exception If an error occurs during the conversion process.
     */
    public static <T> T recYuJiaMsgToSingleObject(String msg, Class<T> clazz) {
        SalmonMsgHeader salmonMsgHeader = null;
        try {
            salmonMsgHeader = SalmonIMsgRecUtil.getYuJiaMsgHeader(msg);
        } catch (Exception e) {
            throw new RuntimeException(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_REC.getDescription(), SalmonMessageType.YUJIA_MSG_REC, "F", msg, "", "", "电文解析阶段", "解析消息头信息失败"));
        }
        String data = CloudEventDecoder.decodeField(msg, "data");
        try {
            return objectMapper.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(SalmonLogUtil.genLogInfo(SalmonMessageType.YUJIA_MSG_REC.getDescription(), SalmonMessageType.YUJIA_MSG_REC, "F", msg, salmonMsgHeader.getSource(), salmonMsgHeader.getSchema(), "电文解析阶段", "解析消息失败，消息体与接收实体类属性不匹配"));
        }
    }

    /**
     * Extracts the header information from a YuJia message.
     *
     * @param msg The YuJia message to process.
     * @return The header information as a YuJiaMsgHeader object.
     * @throws Exception If an error occurs during the extraction process.
     */
    public static SalmonMsgHeader getYuJiaMsgHeader(String msg) throws Exception {
        JsonNode rootNode = objectMapper.readTree(msg);
        String id = rootNode.path("id").asText("");
        String schema = rootNode.path("schema").asText("");
        String source = rootNode.path("source").asText("");
        String type = rootNode.path("type").asText("");
        String timestamp = rootNode.path("timestamp").asText("");
        return new SalmonMsgHeader(id, timestamp, schema, source, type);
    }
}
