package com.oakhole.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
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

    //Operation Permissions
    private List<Operation> operPermissions = Lists.newArrayList();

    //Menu Permissions
    private List<Menu> menuPermissions = Lists.newArrayList();

    //File Permissions
    private List<File> filePermissions = Lists.newArrayList();

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
    @XmlTransient
    @JsonIgnore
    public List<Operation> getOperPermissions() {
        for (Perm perm : this.permissionList) {
            if (perm.getOperation() != null) {
                this.operPermissions.add(perm.getOperation());
            }
        }
        return this.operPermissions;
    }

    @Transient
    @XmlTransient
    @JsonIgnore
    public List<Menu> getMenuPermissions() {
        for (Perm perm : this.permissionList) {
            if (perm.getMenu() != null) {
                this.menuPermissions.add(perm.getMenu());
            }
        }
        return this.menuPermissions;
    }

    /**
     * Those files only to view ,file's code according to the linux's file rule .
     * {@code R} means Read it , {@code W} means Write it , {@code X} means that you can delete it  .
     *
     * U,G,O instead of User owner ,Group and the others .
     *
     * <p>{@code 777} express {@code rwx} satified all operation .</p>
     * @return
     */
    @Transient
    @XmlTransient
    @JsonIgnore
    public List<File> getFilePermissions() {
        for (Perm perm : this.permissionList) {
            if (perm.getFile() != null) {
                this.filePermissions.add(perm.getFile());
            }
        }
        return filePermissions;
    }
}
