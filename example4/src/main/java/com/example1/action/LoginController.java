package com.example1.action;

import com.example1.po.UserBean;
import com.example1.service.UserService;
import com.example1.tools.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        UserBean user = (UserBean)request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login2";
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest request, Model model) {
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> main(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(userName,MD5Util.getMD5(password));
        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        try {
            subject.login(token);   //完成登录
            UserBean user = (UserBean) subject.getPrincipal();
            session.setAttribute("user", user);
            map.put("result", "1");
            map.put("message", "success!");
        }catch (IncorrectCredentialsException ice) {// 捕获密码错误异常
            map.put("result", "0");
            map.put("message", "password error!");
        } catch (UnknownAccountException uae) {// 捕获未知用户名异常
            map.put("result", "0");
            map.put("message", "username error!");
        } catch (ExcessiveAttemptsException eae) {// 捕获错误登录过多的异常
            map.put("result", "0");
            map.put("message", "times error");
        }
        return map;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        HttpSession session = request.getSession();
        return "login2";
    }
}
