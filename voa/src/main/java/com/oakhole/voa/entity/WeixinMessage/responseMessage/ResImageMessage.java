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
public class ResImageMessage extends ResponseMessage {
    private Image Image;

    public ResImageMessage(String toUserName, String fromUserName, ResImageMessage.Image image) {
        super(toUserName, fromUserName, MessageType.IMAGE_MESSAGE);
        Image = image;
    }

    public ResImageMessage(String toUserName, String fromUserName, String mediaId) {
        super(toUserName, MessageType.IMAGE_MESSAGE);
        ResImageMessage.Image image = new Image(mediaId);
        Image = image;
    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }

    public static class Image {

        public Image() {
        }

        public Image(String mediaId) {
            MediaId = mediaId;
        }

        private String MediaId;

        public String getMediaId() {
            return MediaId;
        }

        public void setMediaId(String mediaId) {
            MediaId = mediaId;
        }
    }
}

