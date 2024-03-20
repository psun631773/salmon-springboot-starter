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
package com.salmon.ca.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.salmon.ca.entity.SalmonInfo;
import com.salmon.ca.enums.SalmonMessageType;
import com.salmon.ca.factory.SalmonInfoFactory;
import lombok.extern.slf4j.Slf4j;

import static com.salmon.ca.singleton.SalmonMapperSingleton.objectMapper;


/**
 * @Author sun chongwen
 * @Date 2023-11-10 23:53
 * @PackageName:com.example.springbootproject.yujia.util
 * @ClassName: YuJiaMsgUtil
 * @Description: YuJiaMsgUtil
 * @Version 1.0
 */
@Slf4j
public class SalmonMsgUtil {
    public static  <T> String  msg2YuJiaJson(T messageObject, String topic, String tag) {
        SalmonInfo<T> salmonInfo = SalmonInfoFactory.createYuJiaInfo(tag, topic, SalmonMessageType.YUJIA_MSG_SND.getDescription(), messageObject);
        String yuJiaInfoJson = "";
        try {
            yuJiaInfoJson = objectMapper.writeValueAsString(salmonInfo);
        } catch (JsonProcessingException e) {
            log.error(SalmonLogUtil.genLogInfo("发送", SalmonMessageType.YUJIA_MSG_SND, "F", yuJiaInfoJson, topic, tag, "SND", "转置json格式失败"));

        }
        return yuJiaInfoJson;
    }
}
