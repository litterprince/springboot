package com.example1.sysinterface;

import com.example1.po.UserBean;
import com.example1.service.UserService;
import com.example1.tools.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api(value = "UserInterface", description = "用户接口类")
@Controller
@RequestMapping(value="/api/users")
public class UserInterface {
    @Autowired
    UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value="增加用户", notes="增加用户接口")
    @ApiImplicitParam(paramType = "body", name = "user", value = "用户信息，其中id和createTime不用填写", required = true, dataType = "UserBean")
    @RequestMapping(value="/insert", method= RequestMethod.POST)
    public void insetUser(HttpServletResponse response, @RequestBody UserBean user){
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        try {
            userService.insert(user);
            ResponseUtil.createSuccessResponse(200, "成功", null, response);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            ResponseUtil.createErrorResponse(400, "300", e.getMessage(), response);
        }
    }

    @ApiOperation(value="删除用户", notes="删除用户接口")
    @ApiImplicitParam(paramType = "body", name = "id", value = "用户id", required = true, dataType = "string")
    @RequestMapping(value="/delete", method= RequestMethod.POST)
    public void insetUser(HttpServletResponse response, @RequestBody String id){
        try {
            userService.delete(id);
            ResponseUtil.createSuccessResponse(200, "成功", "", response);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            ResponseUtil.createErrorResponse(400, "300",e.getMessage(), response);
        }
    }

    @ApiOperation(value="获取用户", notes="获取用户接口")
    @ApiImplicitParam(paramType = "body", name = "user", value = "用户信息，其中字段选填", required = true, dataType = "UserBean")
    @RequestMapping(value="/getUserByParam", method= RequestMethod.POST)
    public void getUserByParam(HttpServletResponse response, @RequestBody UserBean user){
        try {
            List<UserBean> list = userService.getUsersByParam(user);
            if(list.size() > 0) {
                ResponseUtil.createSuccessResponse(200, "成功", list, response);
            }else{
                ResponseUtil.createErrorResponse(400, "300","未查询到用户信息", response);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            ResponseUtil.createErrorResponse(400, "300",e.getMessage(), response);
        }
    }
}
