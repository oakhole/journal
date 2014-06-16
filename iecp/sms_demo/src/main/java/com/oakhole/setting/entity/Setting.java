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

package com.oakhole.setting.entity;

import com.oakhole.auth.entity.File;
import com.oakhole.auth.entity.IdEntity;
import com.oakhole.auth.entity.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 绑定用户一些系统设定参数
 *
 * @author oakhole
 * @since 1.0
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
@Table(name = "setting")
public class Setting extends IdEntity {

    private File whiteList;         //白名单列表
    private File blackList;         //黑名单列表

    private int auditCondition;     //审核条件数,0 则不审核
    private int cutCondition;       //扣量起始条件数,0 则不扣量
    private int cutPercent;         //扣量比例，满足起始条件后进行扣量

    private User user;              //用户信息，一对一绑定

    public File getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(File whiteList) {
        this.whiteList = whiteList;
    }

    public File getBlackList() {
        return blackList;
    }

    public void setBlackList(File blackList) {
        this.blackList = blackList;
    }

    public int getAuditCondition() {
        return auditCondition;
    }

    public void setAuditCondition(int auditCondition) {
        this.auditCondition = auditCondition;
    }

    public int getCutCondition() {
        return cutCondition;
    }

    public void setCutCondition(int cutCondition) {
        this.cutCondition = cutCondition;
    }

    public int getCutPercent() {
        return cutPercent;
    }

    public void setCutPercent(int cutPercent) {
        this.cutPercent = cutPercent;
    }

    @OneToOne(mappedBy = "setting", cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
