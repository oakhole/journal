package com.oakhole.auth.repository;

import com.oakhole.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Administrator
 * @since 14-3-24
 */
public interface RoleDao extends PagingAndSortingRepository<Role, Long>, JpaSpecificationExecutor<Role> {
}
