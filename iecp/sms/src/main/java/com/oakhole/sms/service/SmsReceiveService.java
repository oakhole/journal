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

import com.oakhole.core.uitls.DynamicSpecifications;
import com.oakhole.core.uitls.SearchFilter;
import com.oakhole.sms.dao.SmsReceiveDao;
import com.oakhole.sms.entity.SmsReceive;
import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author oakhole
 * @since 1.0
 */
@Service
@Transactional
@Monitored
public class SmsReceiveService {

    @Autowired
    private SmsReceiveDao smsReceiveDao;

    public void create(SmsReceive smsReceive) {
        this.smsReceiveDao.save(smsReceive);
    }

    public SmsReceive get(Long id) {
        return this.smsReceiveDao.findOne(id);
    }

    public void update(SmsReceive smsReceive) {
        this.smsReceiveDao.save(smsReceive);
    }

    public void delete(SmsReceive smsReceive) {
        smsReceive.setDeleted(true);
        this.smsReceiveDao.save(smsReceive);
    }

    public List<SmsReceive> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<SmsReceive> spec = DynamicSpecifications.bySearchFilter(filters.values(), SmsReceive.class);
        return this.smsReceiveDao.findAll(spec);
    }

}
