package com.oakhole.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.oakhole.core.uitls.Collections3;
import com.oakhole.setting.entity.Setting;
import com.oakhole.sms.entity.SmsReceive;
import com.oakhole.sms.entity.SmsTask;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.Set;

/**
 * 系统用户，用户鉴权操作和权限分配
 *
 * @author oakhole
 * @since 1.0
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

    //用户与角色关联，组与角色关联
    private List<Role> roleList = Lists.newArrayList();
    private List<Group> groupList = Lists.newArrayList();

    //Base info
    private Set<Role> roles = Sets.newHashSet();    // 组与用户所有角色之和

    private Set<Menu> menus = Sets.newHashSet();
    private Set<File> files = Sets.newHashSet();
    private Set<Operation> opers = Sets.newHashSet();

    //业务需求,增加设定
    private Setting setting;
    private List<SmsTask> smsTasks = Lists.newArrayList();
    private List<SmsReceive> smsReceives = Lists.newArrayList();

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
        for (Group group : this.groupList) {
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

    /**
     * 获取用户基础信息
     *
     * @return
     */
    @Transient
    @XmlTransient
    @JsonIgnore
    public Set<Menu> getMenus() {
        for (Role role : getRoles()) {
            this.menus.addAll(role.getMenuPermissions());
        }
        return this.menus;
    }

    @Transient
    @XmlTransient
    @JsonIgnore
    public Set<File> getFiles() {
        for (Role role : getRoles()) {
            this.files.addAll(role.getFilePermissions());
        }
        return this.files;
    }

    @Transient
    @XmlTransient
    @JsonIgnore
    public Set<Operation> getOpers() {
        for (Role role : getRoles()) {
            this.opers.addAll(role.getOperPermissions());
        }
        return this.opers;
    }

    /**
     * 单向一对一
     *
     * @return
     */
    @OneToOne(mappedBy = "user")
    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    @OneToMany(targetEntity = SmsTask.class, cascade = CascadeType.ALL, mappedBy = "owner")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @XmlTransient
    @JsonIgnore
    public List<SmsTask> getSmsTasks() {
        return smsTasks;
    }

    public void setSmsTasks(List<SmsTask> smsTasks) {
        this.smsTasks = smsTasks;
    }

    @OneToMany(targetEntity = SmsReceive.class, cascade = CascadeType.ALL, mappedBy = "toUser")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @XmlTransient
    @JsonIgnore
    public List<SmsReceive> getSmsReceives() {
        return smsReceives;
    }

    public void setSmsReceives(List<SmsReceive> smsReceives) {
        this.smsReceives = smsReceives;
    }
}
