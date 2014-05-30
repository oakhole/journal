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

import com.oakhole.voa.utils.MessageType;

/**
 * @author oakhole
 * @since 1.0
 */
public class ResMusicMessage extends ResponseMessage {

    private Music Music;

    public ResMusicMessage(String toUserName, String fromUserName, ResMusicMessage.Music music) {
        super(toUserName, fromUserName, MessageType.MUSIC_MESSAGE);
        Music = music;
    }

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }

    public static class Music {

        private String Title;
        private String Description;
        private String MusicUrl;
        private String HQMusicUrl;
        private String ThumbMediaId;

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

        public String getMusicUrl() {
            return MusicUrl;
        }

        public void setMusicUrl(String musicUrl) {
            MusicUrl = musicUrl;
        }

        public String getHQMusicUrl() {
            return HQMusicUrl;
        }

        public void setHQMusicUrl(String HQMusicUrl) {
            this.HQMusicUrl = HQMusicUrl;
        }

        public String getThumbMediaId() {
            return ThumbMediaId;
        }

        public void setThumbMediaId(String thumbMediaId) {
            ThumbMediaId = thumbMediaId;
        }
    }
}


