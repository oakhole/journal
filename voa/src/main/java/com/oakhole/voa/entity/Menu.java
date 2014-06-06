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

package com.oakhole.voa.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 微信自定义菜单,包含Button,ViewButton,ClickButton,ComplexButton
 *
 * @author oakhole
 * @since 1.0
 */
public class Menu extends ErrorMessage{

    private List<Button> button = Lists.newArrayList();

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    public static class Button {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ViewButton extends Button {
        private String type = "view";
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ClickButton extends Button {
        private String type = "click";
        private String key;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public static class ComplexButton extends Button {
        private List<Button> sub_button = Lists.newArrayList();

        public List<Button> getSub_button() {
            return sub_button;
        }

        public void setSub_button(List<Button> sub_button) {
            this.sub_button = sub_button;
        }
    }
}

