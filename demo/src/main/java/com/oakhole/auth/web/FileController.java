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

package com.oakhole.auth.web;

import com.oakhole.auth.entity.File;
import com.oakhole.auth.service.FileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author oakhole
 * @since 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    /**
     * 单文件上传
     *
     * @param request
     * @param file
     * @return
     */
    @RequestMapping("singleFileUpload")
    @ResponseBody
    public String singleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        String dir = request.getSession().getServletContext().getRealPath("/");
        try {
            this.fileService.createFile(dir, file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            logger.error("上传文件失败:{}", e.getMessage());
            return "上传文件失败";
        }
        return "success";
    }

    @RequestMapping("multiFileUpload")
    @ResponseBody
    public String multiFileUpload(MultipartHttpServletRequest request, @RequestParam("name") String name) {
        List<MultipartFile> files = request.getFiles(name);
        String dir = request.getSession().getServletContext().getRealPath("/");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    this.fileService.createFile(dir, file.getOriginalFilename(), file.getInputStream());
                } catch (IOException e) {
                    logger.error("上传文件失败:{}", e.getMessage());
                }
            }
        }
        return "success";
    }

    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @param fileId   资源ID
     */
    @RequestMapping(value = "download/{fileId}", method = RequestMethod.GET)
    public void fileDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable String fileId) {

        OutputStream outputStream = null;
        File file = this.fileService.getFile(Long.valueOf(fileId));
        if (file == null) {
            logger.error("找不到该目标文件");
        }
        response.reset();
        String fileUrl = request.getSession().getServletContext().getRealPath("/") + file.getUrl();
        java.io.File outputFile = new java.io.File(fileUrl);
        if (!outputFile.exists()) {
            logger.error("所需下载的文件不存在");
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setContentType("application/octet-stream; charset=utf-8");
        try {
            outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(outputFile));
            outputStream.flush();
        } catch (IOException e) {
            logger.error("文件下载错误:{}", e.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error("上传文件时关闭输出流错误:{}", e.getMessage());
                }
            }
        }
    }
}
