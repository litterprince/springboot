package com.example1.action;

import com.example1.po.UserBean;
import com.example1.service.UserService;
import com.example1.tools.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public void index(HttpServletResponse response, Model model) throws Exception{
        sendRedirect("/index", response);
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) throws Exception{
        return "index";
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest request, Model model) throws Exception{
        return "main";
    }

    @RequestMapping("/login")
    public String login(HttpServletResponse response, Model model)throws Exception{
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        boolean remeber = false;
        if(StringUtils.isNotBlank(rememberMe)){
            if(rememberMe.equalsIgnoreCase("on")) remeber = true;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName,MD5Util.getMD5(password), remeber);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);   //完成登录
            UserBean user = (UserBean) subject.getPrincipal();
            return "index";
        }catch (IncorrectCredentialsException ice) {// 捕获密码错误异常
            model.addAttribute("result", "0");
            model.addAttribute("message", "password error!");
        } catch (UnknownAccountException uae) {// 捕获未知用户名异常
            model.addAttribute("result", "0");
            model.addAttribute("message", "username error!");
        } catch (ExcessiveAttemptsException eae) {// 捕获错误登录过多的异常
            model.addAttribute("result", "0");
            model.addAttribute("message", "times error");
        }

        return "/login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/kickout")
    public String kickout(HttpServletResponse response, Model model) throws Exception{
        model.addAttribute("message", "亲，您已被踢出");
        return "error";
    }

    /**
     * URL重定向
     *
     * @param returnUrl
     * @param response
     */
    public void sendRedirect(String returnUrl, HttpServletResponse response) throws Exception{
        if (StringUtils.isNotBlank(returnUrl)) {
            try {
                response.sendRedirect(returnUrl);
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
