package com.springapp.forms;

import com.springapp.entity.Computer;
import com.springapp.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


public class RegisterForm implements Serializable {
    private static final Log logger = LogFactory.getLog(RegisterForm.class);

    private static final long serialVersionUID = 6L;

    private Integer userId;

    @NotBlank
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^([a-zA-Z0-9_-])+$")
    private String login;

    @NotBlank
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^([a-zA-Z])+$")
    private String name;

    @NotBlank
    @Size(min = 2, max = 25)
    @Pattern(regexp = "^([a-zA-Z0-9_-])+$")

    private String password;

    @NotBlank
    @Size(min = 2, max = 25)
    private String passwordConfirm;

    @NotBlank
    @Email
    @Pattern(regexp = "^([a-zA-Z0-9_\\.\\-+])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$")
    private String email;

    @NotBlank
    @Email
    private String emailConfirm;

    private Timestamp regDate;

    private String role;

    private Set<Computer> computers = new LinkedHashSet<Computer>();

    public RegisterForm() {
    }

    public User getUser() {
        User user = new User();
        user.setUserId(userId);
        logger.trace("set user 'userId'" + userId);
        user.setLogin(login);
        logger.trace("set user 'login'" + login);
        user.setEmail(email);
        logger.trace("set user 'email'" + email);
        user.setName(name);
        logger.trace("set user 'name'" + name);
        user.setPassword(password);
        logger.trace("set user 'password'" + password);
        user.setRegDate(new Timestamp(new Date().getTime()));
        logger.trace("set user 'regDate'" + regDate);
        user.setComputers(computers);
        return user;
    }


    public void setUser(User user) {
        logger.trace("Constructor " + user);
        this.userId = user.getUserId();
        logger.trace("Get 'userId' " + getUserId());
        this.login = user.getLogin();
        logger.trace("Get 'login' " + user.getLogin());
        this.password = user.getPassword();
        logger.trace("Get 'password' " + user.getPassword());
        this.name = user.getName();
        logger.trace("get 'name'" + user.getName());
        this.email = user.getEmail();
        logger.trace("Get 'email' " + user.getEmail());
        this.regDate = user.getRegDate();
        logger.trace("Get 'regDate' " + user.getRegDate());
        this.role = user.getRole().getRoleName();
        logger.trace("get 'role'" + user.getRole().getRoleName());
        this.computers = user.getComputers();
        logger.trace("Get 'computers' " + user.getComputers());
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Set<Computer> getComputers() {
        return computers;
    }

    public void setComputers(Set<Computer> computers) {
        this.computers = computers;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
