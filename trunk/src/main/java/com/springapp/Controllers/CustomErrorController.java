package com.springapp.Controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

/**
 * Created by Enot on 15.06.14.
 */
@Controller
public class CustomErrorController {
    private static final Log logger = LogFactory.getLog(CustomErrorController.class);

    @RequestMapping("error")
    public String customError(HttpServletRequest request, HttpServletResponse response, Model model) {
        // retrieve some useful information from the request
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String exceptionMessage = getExceptionMessage(throwable, statusCode);
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
            logger.info("Unknown URI");
        }

        String message = MessageFormat.format("{0} returned for {1} with message {3}",
                statusCode, requestUri, exceptionMessage
        );

        model.addAttribute("errorMessage", message);
        return "customError";
    }

    private String getExceptionMessage(Throwable throwable, Integer statusCode) {
        if (throwable != null) {
            logger.info("Error occured " + throwable.getMessage());
            return throwable.getMessage();
        }
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        logger.info("Error occured " + httpStatus.getReasonPhrase());
        return httpStatus.getReasonPhrase();
    }

}
