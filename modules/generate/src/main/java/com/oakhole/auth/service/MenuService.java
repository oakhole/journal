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

import com.oakhole.auth.dao.MenuDao;
import com.oakhole.auth.entity.Menu;
import com.oakhole.core.uitls.DynamicSpecifications;
import com.oakhole.core.uitls.SearchFilter;
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
public class MenuService {

    private static Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    private Menu menuDao;

    public void create(Menu menu) {
        this.menuDao.save(menu);
    }

    public Menu get(Long id) {
        return this.menuDao.findOne(id);
    }

    public void update(Menu menu) {
        this.menuDao.save(menu);
    }

    public void remove(Menu menu) {
        menu.setDeleted(true);
        this.menuDao.save(menu);
    }

    public List<Menu> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Menu> spec = DynamicSpecifications.bySearchFilter(filters.values(), Menu.class);
        return this.menuDao.findAll(spec);
    }
}
