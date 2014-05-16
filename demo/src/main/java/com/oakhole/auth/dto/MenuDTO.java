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

package com.oakhole.auth.dto;

import com.oakhole.auth.entity.Menu;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Comparator;

/**
 * 用于ztree插件
 * @author Oakhole
 * @since 1.0
 */
public class MenuDTO implements Comparable<MenuDTO>{

    private long id;        //与ztree simpledata 一一对应
    private String name;
    private String url;
    private long pId;

    private boolean hasChild; //判断那是否拥有子节点，用于生成menu

    private boolean open = true;       //expand checked tree , default oepn all
    private boolean checked = false;    //selected the checkbox , default uncheck all

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int compareTo(MenuDTO o) {
        if(this.getId() == o.getId()){
            return 0;
        }else if(this.getId() > o.getId()){
            return -1;
        }else{
            return 1;
        }
    }
}
