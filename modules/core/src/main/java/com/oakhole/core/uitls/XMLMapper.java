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

package com.oakhole.core.uitls;

import com.google.common.collect.Maps;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author oakhole
 * @since 1.0
 */
public class XMLMapper {

    /**
     * SAX方式解析xml,且为二级展示
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
        Map<String, String> map = Maps.newHashMap();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 使用map为类赋值
     *
     * @param map
     * @param dest
     * @param annotationClass
     * @param methodName
     */
    public static <T> T mapper(Map<String, String> map, Class dest,
                                Class annotationClass,
                                  String methodName) {
        T t = null;
        try {
            t = (T)dest.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Field field : Reflections.getAccessibleFields(t)) {
            //判断是否拥有annotation,有则按照annotation去命名，否则按属性名称
            if (field.isAnnotationPresent(annotationClass)) {
                //获取annotation的value值
                String key = (String) Reflections.invokeMethod(annotationClass, methodName,
                        null,
                        null);
                Reflections.setFieldValue(t, field.getName(),
                        map.get(key));
            } else {
                Reflections.setFieldValue(t, field.getName(), map.get(field.getName()));
            }
        }
        return t;
    }
}
