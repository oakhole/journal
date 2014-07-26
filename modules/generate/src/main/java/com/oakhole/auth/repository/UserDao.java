package com.oakhole.auth.repository;

import com.oakhole.auth.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Administrator
 * @since 14-3-6
 */
public interface UserDao extends PagingAndSortingRepository<User,Long>,JpaSpecificationExecutor<User>{

    public User findUserByUsername(String username);
}
