/*
 * Copyright (c) 2013-2014. Powered by http://oakhole.com .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oakhole.voa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author oakhole
 * @since 1.0
 */
@Component
public class BusinessLogger {

    public static final String BUSINESSLOGGER = "business";

    private static Logger logger = LoggerFactory.getLogger(BUSINESSLOGGER);

    /**
     * 业务日志，记录用户做了哪些事情
     * @param fromUserName
     * @param event     事件，如Message,Event
     * @param subEvent  子事件，对事件的补充
     * @param sth       do something...
     */
    public void log(String fromUserName, String event, String subEvent, String sth) {
        logger.info("{}@{}.{}-{}", fromUserName, event, subEvent, sth);
    }
}
