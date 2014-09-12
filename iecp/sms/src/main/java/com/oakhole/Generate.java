/*
 * Copyright (c) 2013-2014. Powered by http://oakhole.com .
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.oakhole;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.util.DefaultClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import javax.persistence.Entity;
import java.beans.Encoder;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 代码生成, 获取entity信息，生成 dao、service、controller、restApi、formView、listView;
 */
public class Generate {

    private static Logger logger = LoggerFactory.getLogger(Generate.class);


    // freemarker 配置信息
    private Configuration configuration;

    public Generate(String templatePath) {
        this.configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.setDefaultEncoding("utf-8");
        configuration.setObjectWrapper(new DefaultObjectWrapper());
    }

    public static void main(String[] args) {

        Generate generate = new Generate("/Users/Oakhole/Documents/Java/journal/iecp/sms/src/main/resources/template");
        generate.generate("/Users/Oakhole/Desktop", "com.oakhole");
    }

    /**
     * 处理数据与模板
     *
     * @param basicPackage
     */
    public void generate(String basicPath, String basicPackage) {

        String file_separator = File.separator;

        try {
            Set<Class<?>> entities = autoScan(basicPackage);

            for (Class entity : entities) {

                Map<String, Object> dataModel = getDataModel(entity);

//                // 处理 Dao
//                String daoPath = basicPath + file_separator
//                        + (dataModel.get("packageName") + ".dao." + dataModel.get("ClassName"))
//                        .replace(".", file_separator) + "Dao.java";
//                File daoFile = new File(daoPath);
//                if (!daoFile.exists()) {
//                    if (!daoFile.getParentFile().exists()) {
//                        daoFile.getParentFile().mkdirs();
//                    }
//                    daoFile.createNewFile();
//                }
//                Template daoTemplate = configuration.getTemplate("dao.ftl");
//                daoTemplate.process(dataModel, new FileWriter(daoPath));
//                logger.info("Dao.create: {}", daoPath);
//
                // 处理 Service
                String servicePath = basicPath + file_separator
                        + (dataModel.get("packageName") + ".service." + dataModel.get("ClassName"))
                        .replace(".", file_separator) + "Service.java";
                File serviceFile = new File(servicePath);
                if (!serviceFile.exists()) {
                    if (!serviceFile.getParentFile().exists()) {
                        serviceFile.getParentFile().mkdirs();
                    }
                    serviceFile.createNewFile();
                }
                Template serviceTemplate = configuration.getTemplate("service.ftl");
                serviceTemplate.process(dataModel, new FileWriter(servicePath));
                logger.info("Service.create: {}", servicePath);

                // 处理 Controller
                String controllerPath = basicPath + file_separator
                        + (dataModel.get("packageName") + ".web." + dataModel.get("ClassName"))
                        .replace(".", file_separator) + "Controller.java";
                File controllerFile = new File(controllerPath);
                if (!controllerFile.exists()) {
                    if (!controllerFile.getParentFile().exists()) {
                        controllerFile.getParentFile().mkdirs();
                    }
                    controllerFile.createNewFile();
                }
                Template controllerTemplate = configuration.getTemplate("controller.ftl");
                controllerTemplate.process(dataModel, new FileWriter(controllerPath));
                logger.info("Controller.create: {}", controllerPath);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 由object得到该类的定义
     * @param object
     * @return
     */
    private Map<String, Object> getDataModel(Class object) {
        Map<String,Object> dataModel = Maps.newHashMap();

        // Step 1: 基础信息
        dataModel.put("author", "Oakhole");
        dataModel.put("since", "1.0");

        // Step 2: 加载实体类获取packageName、className等信息
        String packageName = object.getPackage().getName();

        // 去除最后Entity结尾，便于区分
        packageName = packageName.substring(0, packageName.lastIndexOf("."));

        // 只留尾部名称
        String className = object.getName();
        className = className.substring(className.lastIndexOf(".") + 1, className.length());

        // Step 3: 赋值
        dataModel.put("packageName", packageName);
        dataModel.put("ClassName", className);
        dataModel.put("className", StringUtils.uncapitalize(className));

        return dataModel;
    }

    /**
     * 自动扫描包下的@Entity
     * @param packageToScan
     * @return
     * @throws java.io.IOException
     */
    private Set<Class<?>> autoScan(String packageToScan) throws IOException {

        Set<Class<?>> entities = Sets.newConcurrentHashSet();

        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageToScan
                .replace('.','/'));
        while (dirs.hasMoreElements()) {

            URL url = dirs.nextElement();

            // 判断是否为文件，且有@Entity注解
            if ("file".endsWith(url.getProtocol())) {
                String filePath = URLDecoder.decode(url.getFile(), "utf-8");
                fetchEntities(packageToScan, filePath,entities);
            }
        }

        return entities;
    }

    /**
     * 递归读取包下的Entity类
     * @param filePath
     * @param entities
     */
    private void fetchEntities(String packageName, String filePath, Set<Class<?>> entities) {

        // 定义package
        File file = new File(filePath);

        if (!file.exists() || !file.isDirectory()) {
            logger.warn("{} neither exists nor the directory .",filePath);
            return;
        }

        // 读取package下所有class文件
        File[] subFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.getName().endsWith(".class");
            }
        });

        for (File subFile : subFiles) {

            // 如果是目录，则递归读取，如果是class文件，则判断是否为实体类
            if (subFile.isDirectory()) {
                fetchEntities(packageName + "." + subFile.getName(), subFile.getAbsolutePath(), entities);
            }else {
                // 加载后判断，使用当前线程类加载器加载
                String className = subFile.getName().substring(0, subFile.getName().length() - 6);
                try {
                    Class object = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className);
                    if (object.getAnnotation(Entity.class) != null) {
                        entities.add(object);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /********************* Getter & Setter *********************/
}
