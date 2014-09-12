/*
 * Copyright (c) 2013-2014. Powered by http://oakhole.com .
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.oakhole.syslog.service;

import com.oakhole.advice.dao.AdviceDao;
import com.oakhole.advice.entity.Advice;
import com.oakhole.syslog.dao.SignLogDao;
import com.oakhole.syslog.entity.SignLog;
import com.oakhole.utils.DynamicSpecifications;
import com.oakhole.utils.SearchFilter;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Oakhole
 * @since 1.0
 */
@Service
@Transactional
@Monitored
public class SignLogService{

    private static Logger logger = LoggerFactory.getLogger(SignLogService.class);

    @Autowired
    private SignLogDao signLogDao;

    public void save(SignLog signLog) {
        this.signLogDao.save(signLog);
    }

    public SignLog get(Long id) {
        return this.signLogDao.findOne(id);
    }


    public void remove(SignLog signLog) {
        signLog.setDeleted(true);
        this.signLogDao.save(signLog);
    }

    public Page<SignLog> findAll(Map<String, Object> searchParams, int pageNumber, int pageSize, String
            sortDirection, String sortBy) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<SignLog> spec = DynamicSpecifications.bySearchFilter(filters.values(), SignLog.class);
        Sort sort = new Sort("ASC".equals(sortDirection) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, sort);
        Page<SignLog> signLogList = signLogDao.findAll(spec, pageRequest);
        return signLogList;
    }
}
