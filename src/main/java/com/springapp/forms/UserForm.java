package com.springapp.forms;

import com.springapp.entity.Computer;
import com.springapp.entity.Role;
import com.springapp.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

public class UserForm implements Serializable  {
    private static final Log logger = LogFactory.getLog(UserForm.class);
    private static final long serialVersionUID = 10L;

    private Integer userId;

    @Size(min = 2, max = 25)
    @Pattern(regexp = "^([a-zA-Z0-9_-])+$")
    private String login;

    @Size(min = 2, max = 25)
    @Pattern(regexp = "^([a-zA-Z])+$")
    private String name;

    @Size(min = 2, max = 25)
    @Pattern(regexp = "^([a-zA-Z0-9_-])+$")
    private String password;

    @Email
    @Pattern(regexp = "^([a-zA-Z0-9_\\.\\-+])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$")
    private String email;

    private Timestamp regDate;

    private Role role;

    private Set<Computer> computers;

    public UserForm() {
    }

    public User getUser() {
        User user = new User();
        user.setUserId(userId);
        user.setLogin(login);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRegDate(regDate);
        user.setRole(role);
        user.setComputers(computers);
        return user;
    }

    public void setUser(User user) {
        this.userId = user.getUserId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.regDate = user.getRegDate();
        this.computers = user.getComputers();
        this.role = user.getRole();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Computer> getComputers() {
        return computers;
    }

    public void setComputers(Set<Computer> computers) {
        this.computers = computers;
    }
}
