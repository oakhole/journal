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
 * 用户组，当用户过多不易分配时，可替代
 *
 * @author oakhole
 * @version 4/30/14
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "auth_group")
public class Group extends IdEntity{

    private String name;
    private Group parent;

    //子菜单列表
    private List<Group> childList = Lists.newArrayList();

    //角色列表
    private List<Role> roleList = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    @OneToMany(targetEntity = Group.class, cascade = CascadeType.ALL, mappedBy = "parent")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Group> getChildList() {
        return childList;
    }

    public void setChildList(List<Group> childList) {
        this.childList = childList;
    }

    @ManyToMany
    @JoinTable(name = "auth_role_group", joinColumns = {@JoinColumn(name = "group_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
