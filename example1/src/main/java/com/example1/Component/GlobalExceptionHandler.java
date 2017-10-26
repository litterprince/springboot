package com.example1.Component;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) throws Exception {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("url", req.getRequestURL());
        return DEFAULT_ERROR_VIEW;
    }
}
