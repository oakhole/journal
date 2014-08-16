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

package com.oakhole.auth.service;

import com.oakhole.auth.dao.OperationDao;
import com.oakhole.auth.entity.Operation;
import com.oakhole.utils.DynamicSpecifications;
import com.oakhole.utils.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author Oakhole
 * @since 1.0
 */
@Service
@Transactional
@Monitored
public class OperationService {

    private static Logger logger = LoggerFactory.getLogger(OperationService.class);

    @Autowired
    private OperationDao operationDao;

    public void save(Operation operation) {
        this.operationDao.save(operation);
    }

    public Operation get(Long id) {
        return this.operationDao.findOne(id);
    }


    public void remove(Operation operation) {
        operation.setDeleted(true);
        this.operationDao.save(operation);
    }

    public List<Operation> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Operation> spec = DynamicSpecifications.bySearchFilter(filters.values(), Operation.class);
        return this.operationDao.findAll(spec);
    }
}
