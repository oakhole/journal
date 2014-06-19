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

package com.oakhole.sms.service;

import com.google.common.collect.Lists;
import com.oakhole.sms.entity.Sms;
import com.oakhole.sms.entity.SmsStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext.xml",
        "classpath*:/applicationContext-demo.xml"})
public class SmsServiceTest {

    @Autowired
    private SmsService smsService;

    @Test
    public void testBatchCreateOrUpdate() throws Exception {
        List<Sms> smsList = Lists.newArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int count = 100000;
        Sms sms = null;
        long before = System.currentTimeMillis();
        for (int i = 1; i <= count; i++) {
            sms = new Sms();
            sms.setPhoneNo("18652023713");
            sms.setContent("test");
            sms.setPushTime(simpleDateFormat.format(new Date()));
            sms.setPushStatus(SmsStatus.PUSH_SUCCEED.name());
            smsList.add(sms);
        }

        smsService.batchCreateOrUpdate(smsList);
        long after = System.currentTimeMillis();
        System.out.println("共计消耗时间:" + (after - before));
    }

    @Test
    public void testBatchDelete() throws Exception {

    }
}