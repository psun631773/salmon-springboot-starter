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


import com.salmon.ca.enums.SalmonMessageType;

/**
 * @Author sun chongwen
 * @Date 2023-10-12 17:07
 * @PackageName:com.esb.netty.util
 * @ClassName: LogStrUtil
 * @Description: LogStrUtil
 * @Version 1.0
 */
public class SalmonLogUtil {
    public static String genLogInfo(String messageDirection, SalmonMessageType messageType, String flag, String message, String ctNo, String tag, String status, String reason) {

        if (flag.equals("S")) {
            String currentTimestamp = SalmonDateTimeUtil.getCurrentDateTimeString17();
            String logMessage = messageDirection+"：成功 电文类型："+messageType.getDescription()+" 状态标识：" + status + " 电文来源： " + ctNo + "时间:" + currentTimestamp + " 原因:" + (reason != null && !reason.isEmpty() ? reason : "error") + "  电文主题:" + ctNo + "  电文号:" + tag + "  电文内容:" + message;
            return logMessage;
        } else {
            String currentTimestamp = SalmonDateTimeUtil.getCurrentDateTimeString17();
            String logMessage = messageDirection+"：失败 电文类型："+messageType.getDescription()+" 状态标识：" + status + " 电文来源： " + ctNo + "时间:" + currentTimestamp + " 原因:" + (reason != null && !reason.isEmpty() ? reason : "error") + "  电文内容:" + message;
            return logMessage;
        }

    }


}
