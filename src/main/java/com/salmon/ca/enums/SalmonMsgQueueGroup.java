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
package com.salmon.ca.enums;
/**
 * @Author sun chongwen
 * @Date 2023-09-17 15:39
 * @Version 1.0
 */
/**
 * 枚举类型表示羽嘉消息队列组
 * 包含20个队列组，每个队列组对应一个特定的标识值
 */
public enum SalmonMsgQueueGroup {
    QUEUE_GROUP_01("queueGroup01"), // 队列组 01，对应标识值 "queueGroup01"
    QUEUE_GROUP_02("queueGroup02"), // 队列组 02，对应标识值 "queueGroup02"
    QUEUE_GROUP_03("queueGroup03"), // 队列组 03，对应标识值 "queueGroup03"
    QUEUE_GROUP_04("queueGroup04"), // 队列组 04，对应标识值 "queueGroup04"
    QUEUE_GROUP_05("queueGroup05"), // 队列组 05，对应标识值 "queueGroup05"
    QUEUE_GROUP_06("queueGroup06"), // 队列组 06，对应标识值 "queueGroup06"
    QUEUE_GROUP_07("queueGroup07"), // 队列组 07，对应标识值 "queueGroup07"
    QUEUE_GROUP_08("queueGroup08"), // 队列组 08，对应标识值 "queueGroup08"
    QUEUE_GROUP_09("queueGroup09"), // 队列组 09，对应标识值 "queueGroup09"
    QUEUE_GROUP_10("queueGroup10"), // 队列组 10，对应标识值 "queueGroup10"
    QUEUE_GROUP_11("queueGroup11"), // 队列组 11，对应标识值 "queueGroup11"
    QUEUE_GROUP_12("queueGroup12"), // 队列组 12，对应标识值 "queueGroup12"
    QUEUE_GROUP_13("queueGroup13"), // 队列组 13，对应标识值 "queueGroup13"
    QUEUE_GROUP_14("queueGroup14"), // 队列组 14，对应标识值 "queueGroup14"
    QUEUE_GROUP_15("queueGroup15"), // 队列组 15，对应标识值 "queueGroup15"
    QUEUE_GROUP_16("queueGroup16"), // 队列组 16，对应标识值 "queueGroup16"
    QUEUE_GROUP_17("queueGroup17"), // 队列组 17，对应标识值 "queueGroup17"
    QUEUE_GROUP_18("queueGroup18"), // 队列组 18，对应标识值 "queueGroup18"
    QUEUE_GROUP_19("queueGroup19"), // 队列组 19，对应标识值 "queueGroup19"
    QUEUE_GROUP_20("queueGroup20"); // 队列组 20，对应标识值 "queueGroup20"

    private final String value;

    /**
     * 构造方法，用于为每个队列组设置对应的标识值
     *
     * @param value 队列组的标识值
     */
    SalmonMsgQueueGroup(String value) {
        this.value = value;
    }

    /**
     * 获取队列组的标识值
     *
     * @return 队列组的标识值
     */
    public String getValue() {
        return value;
    }
}
