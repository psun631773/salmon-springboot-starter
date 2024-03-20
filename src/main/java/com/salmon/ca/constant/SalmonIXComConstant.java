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
package com.salmon.ca.constant;

/**
 * @Author sun chongwen
 * @Date 2023-09-19 16:45
 * @PackageName:com.netty.esb.constant
 * @ClassName: IXComConstant
 * @Description: IXComConstant
 * @Version 1.0
 */
public class SalmonIXComConstant {


    //ACK MSG
    public final static String ACK_MSG_LEN = "0000145";// 应答电文长度0000145
    public final static String ACK_MSG_SUCCESS = "A";

    public final static String ACK_MSG_FAIL = "B";

//    public final static String ACK_MSG_CONTROL = " ".repeat(80);// This contains 80 spaces
//    public final static String ACK_MSG_BACKUP = " ".repeat(13);// This contains 80 spaces
//    public final static String ACK_MSG_CONTROL_CT_CONFIG_ERROR = "MSG_CONFIG_ERROR" + " ".repeat(64);// This total length 80
//    public final static String ACK_MSG_CONTROL_RQ_ERROR = "MSG_RKTMQ_ERROR" + " ".repeat(65);// This total length 80
//    public final static String ACK_MSG_CONTROL_DB_ERROR = "MSG_DBASE_ERROR" + " ".repeat(65);// This total length 80
//    public final static String ACK_MSG_CONTROL_VAILD_ERROR = "MSG_VAILD_ERROR" + " ".repeat(65);// This total length 80
    public final static String ACK_MSG_END_MARK = "\n";
    public final static String LOG_STATUS_O = "ORIGINAL";
    public final static String LOG_STATUS_D = "DECODED";
    public final static String LOG_STATUS_E = "ENCODED";
    public final static String LOG_STATUS_S = "SENT";
    public final static String LOG_STATUS_C = "CONSUMED";



}
