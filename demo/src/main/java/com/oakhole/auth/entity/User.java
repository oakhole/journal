package com.oakhole.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.oakhole.core.uitls.Collections3;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统用户，用户鉴权操作和权限分配
 *
 * @author Administrator
 * @since 14-3-6
 */
@SuppressWarnings("ALL")
@Entity
@Table(name = "auth_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity {

    private String name;
    private String username;
    private String password;
    private String plainPassword;
    private String email;
    private String salt;
    private String status;

    private List<Role> roleList = Lists.newArrayList();
    private List<Group> groupList = Lists.newArrayList();

    //shiro needs
    private Set<Role> roles = new HashSet<Role>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @ManyToMany
    @JoinTable(name = "auth_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @ManyToMany
    @JoinTable(name = "auth_user_group", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "group_id")})
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    @Transient
    @XmlTransient
    @JsonIgnore
    public Set<Role> getRoles() {
        this.roles.addAll(this.roleList);
        for(Group group : this.groupList) {
            this.roles.addAll(group.getRoleList());
        }
        return roles;
    }

    @Transient
    @XmlTransient
    @JsonIgnore
    public String getRoleNames() {
        return Collections3.extractToString(getRoles(), "name", ",");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
