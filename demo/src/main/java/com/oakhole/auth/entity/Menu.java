/*
 * Copyright (c) 2014. Power by http://oakhole.com .
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

package com.oakhole.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * 菜单，提供链接url跳转或ajax请求
 *
 * @author oakhole
 * @version 4/30/14
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "auth_menu")
public class Menu extends IdEntity {

    private String name;
    private String url;
    private Menu parent;

    private List<Menu> childList = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @OneToMany(targetEntity = Menu.class, cascade = CascadeType.ALL, mappedBy = "parent")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    @XmlTransient
    public List<Menu> getChildList() {
        return childList;
    }

    public void setChildList(List<Menu> childList) {
        this.childList = childList;
    }
}
