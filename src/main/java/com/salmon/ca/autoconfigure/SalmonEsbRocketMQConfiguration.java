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
package com.salmon.ca.autoconfigure;
import com.salmon.ca.core.SalmonEsbRocketMQConsumer;
import com.salmon.ca.core.SalmonEsbRocketMQProducer;
import com.salmon.ca.properties.SalmonEsbRocketMQProperties;
import com.salmon.ca.redischeck.RedisCheckService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author sun chongwen
 * @Date 2023-09-17 15:39
 * @Version 1.0
 */
@Configuration
@ConditionalOnClass({SalmonEsbRocketMQConsumer.class, SalmonEsbRocketMQProducer.class, com.salmon.ca.redischeck.RedisCheckService.class})
@EnableConfigurationProperties(SalmonEsbRocketMQProperties.class)
public class SalmonEsbRocketMQConfiguration {
//    @Autowired
//    private RedisCheckService redisCheckService;
    @Bean
    public SalmonEsbRocketMQConsumer yuJiaEsbRocketMQConsumer(
            SalmonEsbRocketMQProperties properties, RedisCheckService redisCheckService) {
        SalmonEsbRocketMQConsumer consumer = new SalmonEsbRocketMQConsumer();
        // 在这里可以使用 com.rk.cn.properties 设置消费者的属性
        // 这里假设设置了两个属性，实际使用时可根据实际需要进行扩展
//        consumer.setProperty1(com.rk.cn.properties.getProperty1());
//        consumer.setProperty2(com.rk.cn.properties.getProperty2());
        return consumer;
    }

    @Bean

    public SalmonEsbRocketMQProducer yuJiaEsbRocketMQProducer(SalmonEsbRocketMQProperties properties,
                                                              RocketMQTemplate rocketMQTemplate
           ) {
        SalmonEsbRocketMQProducer producer = new SalmonEsbRocketMQProducer( rocketMQTemplate);

        return producer;
    }

    @Bean
    public RedisCheckService redisCheckService(RedisTemplate<String, String> redisTemplate) {
        return new RedisCheckService(redisTemplate);
    }
}
