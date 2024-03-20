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

/**
 * @Author sun chongwen
 * @Date 2023-11-10 9:12
 * @PackageName:com.util
 * @ClassName: YuJiaStringUtil
 * @Description: YuJiaStringUtil
 * @Version 1.0
 */
public class SalmonStringUtil {
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // 将字符串的第一个字母转换为大写，其余字母转换为小写
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
