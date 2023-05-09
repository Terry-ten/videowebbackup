package com.zr.controller;

import com.zr.pojo.LoginReturns;
import com.zr.pojo.Users;
import com.zr.pojo.Videos;
import com.zr.service.RolePermissionService;
import com.zr.service.UsersService;
import com.zr.service.VideosService;
import com.zr.utils.JwtUtils;
import com.zr.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zr
 * @date 2023/4/11 19:46
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @PostMapping("/login")
    public Result UserLogin(@RequestBody Users user){
        Users u=usersService.userLogin(user);
        if(u!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("name",u.getUsername());
            claims.put("roletype",u.getRoletype());
            String jwt = JwtUtils.generateJwt(claims);
            LoginReturns loginer=new LoginReturns(jwt,u);
            rolePermissionService.showMenuAvaliable(u);
            return Result.success(loginer);
        }
        return Result.error("用户名或密码错误！");
    }
}
