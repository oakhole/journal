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

package com.oakhole.channel.entity;

import com.oakhole.auth.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 通道组，包含三网通道设置,如全网通道可重复使用
 *
 * @author oakhole
 * @since 1.0
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
@Table(name = "channel_group")
public class ChannelGroup extends IdEntity {

    private String name;
    private Channel ctcc;   //中国电信
    private Channel cmcc;   //中国移动
    private Channel cucc;   //中国联通

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ctcc_id")
    public Channel getCtcc() {
        return ctcc;
    }

    public void setCtcc(Channel ctcc) {
        this.ctcc = ctcc;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cmcc_id")
    public Channel getCmcc() {
        return cmcc;
    }

    public void setCmcc(Channel cmcc) {
        this.cmcc = cmcc;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Channel getCucc() {
        return cucc;
    }

    public void setCucc(Channel cucc) {
        this.cucc = cucc;
    }
}
