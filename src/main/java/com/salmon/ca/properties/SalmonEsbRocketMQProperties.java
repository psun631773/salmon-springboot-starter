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
package com.salmon.ca.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @Author sun chongwen
 * @Date 2023-09-17 15:39
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "salmon.yujia.esb")
public class SalmonEsbRocketMQProperties {
    /**
     * RocketMQ 消费者最大重试次数
     */
    private int maxReconsumeTimes = 1;  // 默认值
    /**
     * RocketMQ 消费者暂停当前队列的时间（毫秒）
     */
    private int suspendCurrentQueueTimeMillis = 20000;  // 默认值
    /**
     * RocketMQ 消费者线程最小数
     */
    private int consumeThreadMin = 1;  // 默认值
    /**
     * RocketMQ 消费者线程最大数
     */
    private int consumeThreadMax = 2;  // 默认值

    /**
     * redis 刷新间隔时间 单位为小时
     */
    private int redisRefreshInterval=12;

    // 省略 getter 和 setter 方法

    public int getMaxReconsumeTimes() {
        return maxReconsumeTimes;
    }

    public void setMaxReconsumeTimes(int maxReconsumeTimes) {
        this.maxReconsumeTimes = maxReconsumeTimes;
    }

    public int getSuspendCurrentQueueTimeMillis() {
        return suspendCurrentQueueTimeMillis;
    }

    public void setSuspendCurrentQueueTimeMillis(int suspendCurrentQueueTimeMillis) {
        this.suspendCurrentQueueTimeMillis = suspendCurrentQueueTimeMillis;
    }

    public int getConsumeThreadMin() {
        return consumeThreadMin;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public int getConsumeThreadMax() {
        return consumeThreadMax;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }
    public int getRedisRefreshInterval() {
        return redisRefreshInterval;
    }

    public void setRedisRefreshInterval(int redisRefreshInterval) {
        this.redisRefreshInterval = redisRefreshInterval;
    }
}
