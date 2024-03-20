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
package com.salmon.ca.factory;


import com.salmon.ca.util.SalmonDateTimeUtil;
import com.salmon.ca.entity.SalmonInfo;
import com.salmon.ca.util.SalmonMd5Util;

import static com.salmon.ca.constant.SalmonMsgConstant.DATA_CONTENT_TYPE;
import static com.salmon.ca.constant.SalmonMsgConstant.SPEC_VERSION;

/**
 * @Author sun chongwen
 * @Date 2023-09-17 15:39
 * @Version 1.0
 */
public class SalmonInfoFactory {

    public static <T> SalmonInfo<T> createYuJiaInfo(String schema, String source, String type, T data) {
        SalmonInfo<T> salmonInfo = new SalmonInfo<>();

        if (schema == null || schema.equals("")) {
            throw new RuntimeException("Schema is null or empty");
        }

        if (source == null || source.equals("")) {
            throw new RuntimeException("Source is null or empty");
        }

        if (type == null || type.equals("")) {
            throw new RuntimeException("Type is null or empty");
        }

        if (data == null) {
            throw new RuntimeException("Data is null");
        }

        // If ID is not provided, generate one using Md5Util
        salmonInfo.setId(SalmonMd5Util.genMd5Guid("ctNo", "sysNo"));
        salmonInfo.setTimestamp(SalmonDateTimeUtil.getCurrentDateTimeString17());
        salmonInfo.setSchema(schema);
        salmonInfo.setSource(source);
        salmonInfo.setType(type);
        salmonInfo.setData(data);
        salmonInfo.setDatacontenttype(DATA_CONTENT_TYPE);
        salmonInfo.setSpecversion(SPEC_VERSION);
        return salmonInfo;
    }

    // You can add other com.rk.cn.factory methods as needed for different scenarios or default values.
}
