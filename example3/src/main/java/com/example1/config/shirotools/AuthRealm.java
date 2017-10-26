package com.example1.config.shirotools;

import com.example1.po.PowerBean;
import com.example1.po.RoleBean;
import com.example1.po.UserBean;
import com.example1.service.PowerService;
import com.example1.service.RoleService;
import com.example1.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PowerService powerService;

    //认证登录,返回认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        UserBean user = new UserBean();
        user.setUserName(username);
        List<UserBean> users = userService.getUsersByParam(user);
        if(users.size() > 0) {
            //放入shiro.调用CredentialsMatcher检验密码
            return new SimpleAuthenticationInfo(users.get(0), users.get(0).getPassword(), this.getClass().getName());
        }
        return  null;
    }

    //授权,返回权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        UserBean user = (UserBean) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions = new ArrayList<>();
        List<RoleBean> roles = roleService.getRolesByUser(user.getId());//用户的角色
        if(roles.size()>0) {
            for(RoleBean role : roles) {
                List<PowerBean> powers = powerService.getPowersByRole(role.getId());//角色下的权限
                if(powers.size()>0) {
                    for(PowerBean power : powers) {
                        permissions.add(power.getId());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }
}
