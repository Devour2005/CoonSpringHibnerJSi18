package com.springapp.mvc;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class LoginForm implements Serializable {
    private static final Log logger = LogFactory.getLog(LoginForm.class);
    private static final long serialVersionUID = 123L;

    @NotBlank
    private String login;

    @NotBlank
    private String password;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

