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

import com.oakhole.channel.dao.ChannelDao;
import com.oakhole.channel.entity.Channel;
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
public class ChannelService {

    @Autowired
    private ChannelDao channelDao;

    public void create(Channel channel) {
        this.channelDao.save(channel);
    }

    public Channel get(Long id) {
        return this.channelDao.findOne(id);
    }

    public void update(Channel channel) {
        this.channelDao.save(channel);
    }

    public void delete(Channel channel) {
        channel.setDeleted(true);
        update(channel);
    }

    public List<Channel> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<Channel> spec = DynamicSpecifications.bySearchFilter(filters.values(), Channel.class);
        return this.channelDao.findAll(spec);
    }
}
