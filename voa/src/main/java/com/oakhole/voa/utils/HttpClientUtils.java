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

package com.oakhole.voa.utils;

import com.google.common.collect.Lists;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * 封装httpclient Get请求
 *
 * @author oakhole
 * @since 1.0
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static final Charset CHARSET = Consts.UTF_8;


    /**
     * 处理Get请求,参数分离
     *
     * @param uri
     * @param map
     * @return
     */
    public static HttpEntity get(String uri, Map<String, String> map) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> list = Lists.newArrayList();
        for (String key : map.keySet()) {
            list.add(new BasicNameValuePair(key, map.get(key)));
        }

        String param = URLEncodedUtils.format(list, CHARSET);
        String url = uri + "?" + param;
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            httpResponse.close();
            return httpResponse.getEntity();
        } catch (IOException e) {
            logger.error("请求异常:{}", e.getMessage());
        }
        return null;
    }

    /**
     * 参数不需要map
     *
     * @param uri
     * @return
     */
    public static HttpEntity get(String uri) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            return httpResponse.getEntity();
        } catch (IOException e) {
            logger.error("请求异常:{}", e.getMessage());
        }
        return null;
    }

    public static String getString(String uri) {
        HttpEntity entity = get(uri);
        if (entity != null) {
            try {
                return EntityUtils.toString(entity, CHARSET);
            } catch (IOException e) {
                logger.error("请求异常:{}", e.getMessage());
            }
        }
        return "";
    }

    /**
     * 处理get请求后返回String
     *
     * @param uri
     * @param map
     * @return
     */
    public static String getString(String uri, Map<String, String> map) {
        HttpEntity entity = get(uri, map);
        try {
            if (entity != null) {
                return EntityUtils.toString(entity, CHARSET);
            }
        } catch (IOException e) {
            logger.error("请求异常:{}", e.getMessage());
        }
        return "";
    }

    /**
     * 处理get请求返回文件资源
     *
     * @param uri
     * @param file
     * @return
     */
    public static String getFile(String uri, File file) {
        HttpEntity entity = get(uri);
        if (entity != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                InputStream inputStream = entity.getContent();
                //输入到文件
                byte[] bytes = new byte[1024];
                int length = 0;
                while (((length = inputStream.read(bytes)) != -1)) {
                    fileOutputStream.write(bytes, 0, length);
                }
                //刷新缓冲区
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                logger.error("请求异常:{}", e.getMessage());
            }
        }
        return "";
    }

    /**
     * 普通请求或多文本post请求
     *
     * @param uri
     * @param map
     * @return
     */
    public static String post(String uri, Map<String, Object> map) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        HttpEntity httpEntity = null;
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value != null && value instanceof String) {
                String param = (String) value;
                multipartEntityBuilder.addTextBody(key, param, ContentType.create("text/plain", CHARSET));
            }
            if (value != null && value instanceof File) {
                multipartEntityBuilder.addBinaryBody(key, (File) value);
            }
        }
        try {
            httpPost.setEntity(multipartEntityBuilder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                httpEntity = httpResponse.getEntity();
            }
        } catch (IOException e) {
            logger.error("请求异常:{}", e.getMessage());
        }
        try {
            return EntityUtils.toString(httpEntity, CHARSET);
        } catch (IOException e) {
            logger.error("请求异常:{}", e.getMessage());
        }
        return "";
    }

    /**
     * 直接发送字符串请求
     *
     * @param uri
     * @param params
     * @return
     */
    public static String post(String uri, String params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        StringEntity stringEntity = new StringEntity(params, CHARSET.toString());
        httpPost.setEntity(stringEntity);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                httpResponse.close();
                return EntityUtils.toString(httpResponse.getEntity(), CHARSET);
            }
        } catch (IOException e) {
            logger.error("请求异常:{}", e.getMessage());
        }
        return "";
    }

    public static String post(String uri, File file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        try {
            InputStreamEntity inputStreamEntity = new InputStreamEntity(new FileInputStream(file));
            //读取文件流发送
            httpPost.setEntity(inputStreamEntity);
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
                httpResponse.close();
                return EntityUtils.toString(httpResponse.getEntity());
            } catch (IOException e) {
                logger.error("请求异常:{}", e.getMessage());
            }
        } catch (FileNotFoundException e) {
            logger.error("请求异常:{}", e.getMessage());
        }
        return "";
    }
}
