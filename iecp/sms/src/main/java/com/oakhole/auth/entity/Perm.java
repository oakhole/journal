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
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

/**
 * 权限资源，与各类资源实体相关联，且与用户组和角色关联，针对或相对进行权限分配
 *
 * @author oakhole
 * @version 4/30/14
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "auth_perm")
public class Perm extends IdEntity{
    private String type;

    //具体权限
    private Menu menu;
    private File file;
    private Operation operation;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "auth_perm_menu",joinColumns = {@JoinColumn(name = "perm_id")},inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "auth_perm_file",joinColumns = {@JoinColumn(name = "perm_id")},inverseJoinColumns = {@JoinColumn(name = "file_id")})
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "auth_perm_oper",joinColumns = {@JoinColumn(name = "perm_id")},inverseJoinColumns = {@JoinColumn(name = "oper_id")})
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
