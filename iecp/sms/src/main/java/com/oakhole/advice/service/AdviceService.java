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

package com.oakhole.advice.service;

import com.oakhole.advice.dao.AdviceDao;
import com.oakhole.advice.entity.Advice;
import com.oakhole.core.uitls.DynamicSpecifications;
import com.oakhole.core.uitls.SearchFilter;
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
public class AdviceService {

    @Autowired
    private AdviceDao adviceDao;

    public void create(Advice advice) {
        this.adviceDao.save(advice);
    }

    public Advice get(Long id) {
        return this.adviceDao.findOne(id);
    }

    public void update(Advice advice) {
        this.adviceDao.save(advice);
    }

    public void remove(Advice advice) {
        advice.setDeleted(true);
        this.adviceDao.save(advice);
    }

    public List<Advice> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Advice> spec = DynamicSpecifications.bySearchFilter(filters.values(), Advice.class);
        return this.adviceDao.findAll(spec);
    }
}
