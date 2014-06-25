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

package com.oakhole.channel.service;

import com.oakhole.channel.dao.ChannelGroupDao;
import com.oakhole.channel.entity.ChannelGroup;
import com.oakhole.core.uitls.DynamicSpecifications;
import com.oakhole.core.uitls.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author oakhole
 * @since 1.0
 */
public class ChannelGroupService {

    @Autowired
    private ChannelGroupDao channelGroupDao;

    public void create(ChannelGroup channelGroup) {
        this.channelGroupDao.save(channelGroup);
    }

    public ChannelGroup get(Long id) {
        return this.channelGroupDao.findOne(id);
    }

    public void update(ChannelGroup channelGroup) {
        this.channelGroupDao.save(channelGroup);
    }

    public void delete(ChannelGroup channelGroup) {
        channelGroup.setDeleted(true);
        this.channelGroupDao.save(channelGroup);
    }

    public List<ChannelGroup> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<ChannelGroup> spec = DynamicSpecifications.bySearchFilter(filters.values(), ChannelGroup.class);
        return this.channelGroupDao.findAll(spec);
    }
}
