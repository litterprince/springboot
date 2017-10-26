package com.example1.action;

import com.example1.po.PersonBean;
import com.example1.po.UserBean;
import com.example1.service.LoginService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LoginService loginService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        UserBean user = (UserBean)request.getSession().getAttribute("user");
        List<PersonBean> people = new ArrayList<>();
        PersonBean p1 = new PersonBean("zhangsan", 11);
        PersonBean p2 = new PersonBean("lisi", 22);
        PersonBean p3 = new PersonBean("wangwu", 33);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        model.addAttribute("user", user);
        model.addAttribute("people", people);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
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
        if(!userName.equals("") && !password.equals("")){
            UserBean user = new UserBean();
            user.setUserName(userName);
            user.setPassword(password);
            List<UserBean> users = loginService.getUsersByParam(user);
            if(users.size() > 0) {
                request.getSession().setAttribute("user", user);
                map.put("result", "1");
            }else{
                map.put("result","0");
            }
        }else{
            map.put("result","0");
        }
        return map;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        if (null != session) {
            session.removeAttribute("user");
            session.invalidate();
        }else{
        }
        return "login";
    }
}
