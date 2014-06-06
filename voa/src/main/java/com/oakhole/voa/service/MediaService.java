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

package com.oakhole.voa.service;

import com.oakhole.voa.entity.AccessToken;
import com.oakhole.voa.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 多媒体文件的上传下载
 *
 * @author oakhole
 * @since 1.0
 */
@Service
public class MediaService {

    private static final String upload_url = "http://file.api.weixin.qq.com/cgi-bin/media/upload" +
            "?access_token=ACCESS_TOKEN&type=TYPE";
    private static final String download_url = "http://file.api.weixin.qq.com/cgi-bin/media/get" +
            "?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

    private static Logger logger = LoggerFactory.getLogger(MediaService.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private AccessToken accessToken;

    /**
     * 上传文件，返回文件类型和资源ID
     *
     * @param file
     * @return
     */
    public String upload(File file, String fileType) {
        authService.generateAccessToken();
        String sourceUri = upload_url.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        String uri = sourceUri.replace("TYPE", fileType);
        HttpClientUtils.post(uri, file);
        return "";
    }

    /**
     * Get请求文件
     *
     * @param file
     * @return
     */
    public String download(String mediaId, File file) {
        authService.generateAccessToken();
        String sourceUri = download_url.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        String uri = sourceUri.replace("MEDIA_ID", mediaId);
        HttpClientUtils.getFile(uri, file);
        return "";
    }
}
