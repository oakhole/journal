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
public class ResVoiceMessage extends ResponseMessage {

    private Voice voice;

    public ResVoiceMessage(String toUserName, String fromUserName, Voice voice) {
        super(toUserName, fromUserName, MessageType.VOICE_MESSAGE);
        this.voice = voice;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public static class Voice {
        private String mediaId;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
    }

}

