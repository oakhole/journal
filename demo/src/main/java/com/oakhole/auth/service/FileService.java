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

package com.oakhole.auth.service;

import com.oakhole.auth.entity.File;
import com.oakhole.auth.repository.FileDao;
import org.apache.commons.io.FileUtils;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 资源的管理
 *
 * @author oakhole
 * @since 1.0
 */
@Service
@Transactional
@Monitored
public class FileService {

    //设置资源默认权限
    public static final String DEFAULT_FILE_CODE = "644";

    Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileDao fileDao;


    /**
     * 按资源ID查找
     *
     * @param id
     * @return
     */
    public File getFile(long id) {
        return this.fileDao.findOne(id);
    }

    /**
     * 资源存储
     *
     * @param dir
     * @param name
     * @param inputStream
     */
    public void createFile(String dir, String name, InputStream inputStream) {
        File file = copyFile(dir, name, inputStream);
        this.fileDao.save(file);
    }


    /**
     * 按资源ID删除资源
     *
     * @param file
     */
    public void removeFile(File file) {
        file.setDeleted(true);
        this.fileDao.save(file);
    }


    /**
     * 查询所有资源
     *
     * @return
     */
    public List<File> findAllFiles() {
        return (List<File>) this.fileDao.findAll();
    }

    /**
     * copy上传的资源文件到本地目录
     *
     * @param dir
     * @param inputStream
     * @return
     */
    private File copyFile(String dir, String name, InputStream inputStream) {

        File file = null;
        String fileUrl = generateFileUrl();
        java.io.File newFile = new java.io.File(dir + fileUrl);
        if (!newFile.exists()) {
            if (newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                logger.error("创建文件失败:{}", e.getMessage());
                return null;
            }
        }
        try {
            FileUtils.copyInputStreamToFile(inputStream, newFile);
        } catch (IOException e) {
            logger.error("拷贝文件失败:{}", e.getMessage());
            return null;
        }
        //默认设置资源权限为644
        file = new File(name, DEFAULT_FILE_CODE, fileUrl);
        return file;
    }

    /**
     * 生成fileUrl
     *
     * @return
     */
    private String generateFileUrl() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String fileName = String.valueOf(System.currentTimeMillis());
        return "upload/" + simpleDateFormat.format(new Date()) + "/" + fileName;
    }
}
