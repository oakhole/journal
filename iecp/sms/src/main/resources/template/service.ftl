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

package ${packageName}.service;

import ${packageName}.dao.${ClassName}Dao;
import ${packageName}.entity.${ClassName};
import com.oakhole.utils.DynamicSpecifications;
import com.oakhole.utils.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author ${author}
 * @since ${since}
 */
@Service
@Transactional
@Monitored
public class ${ClassName}Service {

    private static Logger logger = LoggerFactory.getLogger(${ClassName}Service.class);

    @Autowired
    private ${ClassName}Dao ${className}Dao;

    public void save(${ClassName} ${className}) {
        this.${className}Dao.save(${className});
    }

    public ${ClassName} get(Long id) {
        return this.${className}Dao.findOne(id);
    }


    public void remove(${ClassName} ${className}) {
        ${className}.setDeleted(true);
        this.${className}Dao.save(${className});
    }

    public List<${ClassName}> findAll(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<${ClassName}> spec = DynamicSpecifications.bySearchFilter(filters.values(), ${ClassName}.class);
        return this.${className}Dao.findAll(spec);
    }
}
