package com.example1.action;

import com.alibaba.fastjson.JSONObject;
import com.example1.po.UserBean;
import com.example1.service.UserService;
import com.example1.tools.ResponseUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, ModelMap model){
        return "users/index";
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> query(HttpServletRequest request, @RequestParam Integer offset, @RequestParam Integer limit){
        offset = offset == null ? 1 : offset;
        limit = limit == null ? 10 : limit;
        PageHelper.startPage((Integer) offset, (Integer) limit);
        List<UserBean> users = userService.getAll();
        Map<String, Object> map = new HashMap<String, Object>();
        String total = String.valueOf(userService.getCount());
        map.put("total", total);
        map.put("rows", users);
        return map;
    }

    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request, ModelMap model){
        return "users/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, ModelMap model){
        String id = request.getParameter("id");
        UserBean user = userService.getOne(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(HttpServletRequest request, HttpServletResponse response, UserBean user){
        JSONObject result = new JSONObject();
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        userService.insert(user);
        result.put("result", "1");
        ResponseUtil.createSuccessResponse(200, result, response);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response, UserBean user){
        JSONObject result = new JSONObject();
        user.setCreateTime(new Date());
        userService.update(user);
        result.put("result", "1");
        ResponseUtil.createSuccessResponse(200, result, response);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(HttpServletRequest request, HttpServletResponse response){
        String ids = request.getParameter("ids");
        JSONObject result = new JSONObject();
        userService.batchDeleteUsers(ids);
        result.put("result", "1");
        ResponseUtil.createSuccessResponse(200, result, response);
    }
}
