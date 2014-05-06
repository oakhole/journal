package com.oakhole.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Administrator
 * @since 14-3-17
 */
@SuppressWarnings("ALL")
@Entity
@Table(name = "auth_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends IdEntity {

    private String name;

    private List<Perm> permissionList = Lists.newArrayList();

    //shiro need
    private List<String> permissions = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "auth_role_perm", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "perm_id")})
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Perm> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Perm> permissionList) {
        this.permissionList = permissionList;
    }

    @Transient
    @JsonIgnore
    public List<String> getPermissions() {
        for (Perm perm : this.permissionList) {
            for (Operation oper : perm.getOperationList()) {
                //shiro only need operation permissionList
                this.permissions.add(oper.getCode());
            }
        }
        return this.permissions;
    }
}
