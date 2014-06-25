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
import com.oakhole.sms.dao.SmsTaskDao;
import com.oakhole.sms.entity.SmsTask;
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
public class SmsTaskService {

    @Autowired
    private SmsTaskDao smsTaskDao;


    public void create(SmsTask smsTask) {
        this.smsTaskDao.save(smsTask);
    }

    public SmsTask get(Long id) {
        return this.smsTaskDao.findOne(id);
    }

    public void update(SmsTask smsTask) {
        this.smsTaskDao.save(smsTask);
    }

    public void delete(SmsTask smsTask) {
        smsTask.setDeleted(true);
        this.smsTaskDao.save(smsTask);
    }

    public List<SmsTask> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<SmsTask> spec = DynamicSpecifications.bySearchFilter(filters.values(), SmsTask.class);
        return this.smsTaskDao.findAll(spec);
    }
}
