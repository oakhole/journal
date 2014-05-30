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

package com.oakhole.voa.entity.WeixinMessage.responseMessage;

import com.google.common.collect.Lists;
import com.oakhole.voa.utils.MessageType;

import java.util.List;

/**
 * @author oakhole
 * @since 1.0
 */
public class ResNewsMessage extends ResponseMessage {
    private String ArticleCount;   //限制在10条以内
    private List<Article> Articles = Lists.newArrayList();

    public ResNewsMessage(String toUserName, String fromUserName, List<Article> articles) {
        super(toUserName, fromUserName, MessageType.NEWS_MESSAGE);
        Articles = articles;
        ArticleCount = String.valueOf(Articles.size());
    }

    public String getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }

    public static class Article {
        private String Title;   //图文消息标题
        private String Description; //图文消息描述
        private String PicUrl;  //图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
        private String Url; //点击图文消息跳转链接

        public Article() {
        }

        public Article(String title, String description, String picUrl, String url) {
            Title = title;
            Description = description;
            PicUrl = picUrl;
            Url = url;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getPicUrl() {
            return PicUrl;
        }

        public void setPicUrl(String picUrl) {
            PicUrl = picUrl;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }
    }
}


