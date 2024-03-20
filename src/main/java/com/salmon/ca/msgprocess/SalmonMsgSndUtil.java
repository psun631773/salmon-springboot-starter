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



import com.salmon.ca.standard.CloudEventEnCoder;
import com.salmon.ca.util.SalmonDateTimeUtil;
import com.salmon.ca.util.SalmonMd5Util;

import static com.salmon.ca.constant.SalmonMsgConstant.DATA_CONTENT_TYPE;
import static com.salmon.ca.constant.SalmonMsgConstant.SPEC_VERSION;
import static com.salmon.ca.singleton.SalmonMapperSingleton.objectMapper;


/**
 * @Author sun chongwen
 * @Date 2023-10-27 8:14
 * @PackageName:com.ctutil
 * @ClassName: YuJianMsgSndUtil
 * @Description: YuJianMsgSndUtil
 * @Version 1.0
 */
public class SalmonMsgSndUtil {

    public static String sndYuJiaMsg(String ctNo,Object obj,String sysCode,String type) throws Exception {

        String jsonString = objectMapper.writeValueAsString(obj);
        String convertedToCloudEvent = CloudEventEnCoder.convertToCloudEvent(jsonString, SalmonMd5Util.genMd5Guid(ctNo, sysCode), SalmonDateTimeUtil.getCurrentDateTimeString17(), ctNo, type, sysCode, SPEC_VERSION, DATA_CONTENT_TYPE);
        return convertedToCloudEvent;
    }
}
