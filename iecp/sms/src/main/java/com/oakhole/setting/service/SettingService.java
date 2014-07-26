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

package com.oakhole.setting.service;

import com.oakhole.core.uitls.DynamicSpecifications;
import com.oakhole.core.uitls.SearchFilter;
import com.oakhole.setting.dao.SettingDao;
import com.oakhole.setting.entity.Setting;
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
public class SettingService {

    @Autowired
    private SettingDao settingDao;

    public void create(Setting setting) {
        this.settingDao.save(setting);
    }

    public Setting get(Long id) {
        return this.settingDao.findOne(id);
    }

    public void update(Setting setting) {
        this.settingDao.save(setting);
    }

    public void delete(Setting setting) {
        setting.setDeleted(true);
        this.settingDao.save(setting);
    }

    public List<Setting> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Setting> spec = DynamicSpecifications.bySearchFilter(filters.values(), Setting.class);
        return this.settingDao.findAll(spec);
    }
}
