package com.springapp.Entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

// must be serializable to be attribute on session
@Entity
@Table(name = "members")
@NamedQueries({
        @NamedQuery(name = "User.getAllUsers", query = "SELECT u FROM User u")})

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;

    @NotBlank(message = "Login can't be empty!")
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @NotBlank(message = "Password can't be empty!")
    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "regdate")
    private Timestamp regDate;

    // ROLES
    @SuppressWarnings("unchecked")
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @JoinColumn(name = "role_id")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User() {
    }

    //COMPUTERS
    @SuppressWarnings("unchecked")
    @ManyToMany(fetch = FetchType.EAGER/*, cascade = {CascadeType.ALL}*/)
    @JoinTable(name = "usercomp",
            joinColumns = {
                    @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "comp_id")})
    private Set<Computer> computers = new LinkedHashSet<>();

    public Set<Computer> getComputers() {
        return computers;
    }

    public void setComputers(Set<Computer> computers) {
        this.computers = computers;
    }

    public void setUserId(Integer userid) {
        this.userId = userid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    @Override
    public boolean equals(Object ref) {
        if (ref == null || getClass() != ref.getClass()) {
            return false;
        }
        User that = (User) ref;
        if (this.userId != that.userId) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, name, password, email);
    }

    @Override
    public String toString() {
        return new StringBuilder("User {Id= '")
                .append(userId)
                .append("\'")
                .append(", login= '")
                .append(login)
                .append("\'")
                .append(", password= '")
                .append(password)
                .append("\'")
                .append(", name= '")
                .append(name)
                .append("\'")
                .append(", email= '")
                .append(email)
                .append("\'")
                .append(", regDate= '")
                .append(regDate)
                .append("\'")
                .append("}\r\n").toString();


       /* return "User {login= '" + login + "\'"
                + ", name= '" + name + "\'"
                + ", email= '" + email + "\'"
//                + ", password= '" + password + "\'"
                + ", regDate= '" + regDate + "\'"
                + "'}\r\n";   */
    }
}


