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

import com.google.common.collect.Lists;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * 提供相关操作，eg：sys:user:view
 *
 * @author oakhole
 * @version 4/30/14
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "auth_oper")
public class Operation extends IdEntity{

    private String name;
    private String code;
    private String url_prefix;
    private Operation parent;

    private List<Operation> childList = Lists.newArrayList();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl_prefix() {
        return url_prefix;
    }

    public void setUrl_prefix(String url_prefix) {
        this.url_prefix = url_prefix;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Operation getParent() {
        return parent;
    }

    public void setParent(Operation parent) {
        this.parent = parent;
    }

    @OneToMany(targetEntity = Operation.class,cascade = CascadeType.ALL,mappedBy = "parent")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Operation> getChildList() {
        return childList;
    }

    public void setChildList(List<Operation> childList) {
        this.childList = childList;
    }
}
