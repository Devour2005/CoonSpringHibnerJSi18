package com.springapp.Controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LogoutController {
    //Log4j
    private static final Logger logger = Logger.getLogger(LogoutController.class);

    @RequestMapping(value = "/logout.do")
    public String logout(HttpSession session, Model model,
                         HttpServletRequest request) throws IOException {
        try {
            session = request.getSession(false);
            if (session != null) {
                session.removeAttribute("login");
                session.removeAttribute("password");
                session.invalidate();
                logger.info("User logged out...");
            }
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("logout error!!", e);
            return "redirect:/login";
        }
    }
}
