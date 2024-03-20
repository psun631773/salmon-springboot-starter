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


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @Author sun chongwen
 * @Date 2023-09-17 15:39
 * @Version 1.0
 */

public class SalmonMd5Util {
    public static String genMd5Guid(String ctNo,String sysNo){
        String date = SalmonDateTimeUtil.getCurrentDateTimeString17();
        String guidStr = ctNo+sysNo+date;
        String guid = SalmonMd5Util.md5Process(guidStr);
        return guid;
    }

    public static String md5Process(String uuidString) {

        try {
            // 获取 MD5 摘要算法实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算摘要
            byte[] mdBytes = md.digest(uuidString.getBytes());

            // 将摘要转换为十六进制字符串（长度为32个字符）
            StringBuilder sb = new StringBuilder();
            for (byte mdByte : mdBytes) {
                sb.append(Integer.toString((mdByte & 0xff) + 0x100, 16).substring(1));
            }
            // 截取前16个字符，以确保结果长度为16字节
            String result = sb.toString().substring(0, 16);

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
