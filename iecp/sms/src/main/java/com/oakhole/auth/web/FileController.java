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

import com.google.common.collect.Maps;
import com.oakhole.auth.entity.File;
import com.oakhole.auth.entity.Group;
import com.oakhole.auth.entity.Role;
import com.oakhole.auth.entity.User;
import com.oakhole.auth.service.FileService;
import com.oakhole.auth.service.ShiroDbRealm;
import com.oakhole.auth.service.UserService;
import com.oakhole.utils.Servlets;
import org.apache.jasper.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Map;

/**
 * @author Oakhole
 * @since 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileService.class);

    private static Map<String, String> perm_code = Maps.newHashMap();

    static {
        perm_code.put("0", "");
        perm_code.put("1", "x");
        perm_code.put("2", "w");
        perm_code.put("3", "wx");
        perm_code.put("4", "r");
        perm_code.put("5", "rx");
        perm_code.put("6", "rw");
        perm_code.put("7", "rwx");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = {"", "list"})
    public String list(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                       @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy, Model model,
                       ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<File> files = this.fileService.findAll(searchParams, pageNumber, pageSize, sortDirection, sortBy);
        model.addAttribute("files", files);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "file/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "file/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid File file, RedirectAttributes redirectAttributes) {
        this.fileService.save(file);
        redirectAttributes.addFlashAttribute("message", "添加成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/file";
    }

    /**
     * 专程pdf后进行页面展示，内容量过大的，不给予显示
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("file", fileService.get(id));
        return "file/show";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("file", fileService.get(id));
        return "file/update";
    }

    /**
     * web编辑器，基于open office等开源工具
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "file") File file, RedirectAttributes redirectAttributes) {
        this.fileService.save(file);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/file";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        this.fileService.remove(fileService.get(id));
        redirectAttributes.addFlashAttribute("message", "删除成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/file";
    }

    @RequestMapping(value = "download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") Long id, HttpServletRequest request,
                         HttpServletResponse response) {

        File file = this.fileService.get(id);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        // 判断当前用户是否拥有读和执行权限
        if (auth(file, "rx")) {
            java.io.File realFile = new java.io.File(file.getUrl());

            String realName = file.getName();
            long fileLength = realFile.length();

            // 设置响应格式
            response.setContentType("text/plain");
            try {
                response.setHeader("Content-disposition", "attachment; filename=" +
                        new String(realName.getBytes("utf-8"), "ISO8859-1"));
            } catch (UnsupportedEncodingException e) {
                logger.error("不支持的字符编码: {}", e.getMessage());
            }
            response.setHeader("Content-Length", String.valueOf(fileLength));

            try {
                bis = new BufferedInputStream(new FileInputStream(realFile));
            } catch (FileNotFoundException e) {
                logger.error("未找到文件: {}", e.getMessage());
            }
            try {
                bos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (IOException e) {
                logger.error("下载文件时发生io读写错误：{}", e.getMessage());
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (bos != null) {
                        bos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 采用 linux 文件权限管理方法
     *
     * @param file      请求验证的资源
     * @param auth_perm 请求验证资源所对应的权限
     * @return true, 拥有该权限; false, 没有权限
     */
    private boolean auth(File file, String auth_perm) {

        // 获取所请求资源的权限代码
        String pcode = file.getCode();

        // 分别获取u g o 各具有的权限
        char u = pcode.charAt(0);
        char g = pcode.charAt(1);
        char o = pcode.charAt(2);

        // 1. 获取当前 session 用户
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        User currentUser = this.userService.findUserByUsername(shiroUser.getLoginName());

        // 2. 判断当前访问用户与所请求资源的关系
        if (currentUser.getFiles().contains(file)) {
            // U
            if (perm_code.containsKey(u)) {
                // 3. 进行验证返回
                return perm_code.get(u).contains(auth_perm);
            }
        } else {
            // G
            for (Group group : currentUser.getGroupList()) {
                for (Role role : group.getRoleList()) {
                    if (role.getFilePermissions().contains(file)) {
                        return perm_code.get(g).contains(auth_perm);
                    }
                }
            }
        }

        // O
        return perm_code.get(o).contains(auth_perm);
    }

    @ModelAttribute
    public void getFile(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("file", this.fileService.get(id));
        }
    }
}
