package com.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @Column(name = "role_id", unique = true, nullable = false)
    private Integer role_id;

    @SuppressWarnings("unchecked")
    @OneToMany(mappedBy = "role",
            fetch = FetchType.EAGER/*,cascade = CascadeType.ALL*/)
    private Set<User> users = new HashSet();

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }

    @Column(name = "roleName", unique = true, nullable = false)
    private String roleName;

    public Role() {
    }

    public Role(String roleName, Set<User> users) {
        this.roleName = roleName;
        this.users = users;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRole_id(Integer id) {
        this.role_id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String name) {
        this.roleName = name;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + role_id +
                ", name='" + roleName + '\'' +
                '}';
    }
}