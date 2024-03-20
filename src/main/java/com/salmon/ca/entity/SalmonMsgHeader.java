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
package com.salmon.ca.entity;

import lombok.Data;

/**
 * @Author sun chongwen
 * @Date 2023-10-27 10:55
 * @PackageName:com.entity
 * @ClassName: YuJiaMsgHeader
 * @Description: YuJiaMsgHeader
 * @Version 1.0
 */

@Data
public class SalmonMsgHeader {
    String id;
    String timestamp;
    String schema;
    String source;
    String type;

    public SalmonMsgHeader(String id, String timestamp, String schema, String source, String type) {
        this.id = id;
        this.timestamp = timestamp;
        this.schema = schema;
        this.source = source;
        this.type = type;
    }
    public SalmonMsgHeader(){

    }
}
