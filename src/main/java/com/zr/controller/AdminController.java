//package com.zr.controller;
//
//import com.zr.pojo.Admins;
//import com.zr.pojo.LoginReturns;
//import com.zr.pojo.Users;
//import com.zr.service.AdminsService;
//import com.zr.service.RolePermissionService;
//import com.zr.utils.JwtUtils;
//import com.zr.utils.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author zr
// * @date 2023/4/15 15:02
// */
//@Slf4j
//@RestController
//public class AdminController {
//
//    @Autowired
//    private AdminsService adminsService;
//
//    @Autowired
//    private RolePermissionService rolePermissionService;
//    @PostMapping("/admin")
//     public Result adminLogin(@RequestBody Admins admin){
//        Admins a=adminsService.adminLogin(admin);
//        if(a!=null){
//        Map<String,Object> claims=new HashMap<>();
//        log.info("管理员登录的roletype:"+a.getRoletype());
//        claims.put("id",a.getId());
//        claims.put("name",a.getUsername());
//        claims.put("roletype",a.getRoletype());
//        String jwt = JwtUtils.generateJwt(claims);
//        LoginReturns loginer=new LoginReturns(jwt,a);
//        log.info("即将对数据库进行刷新操作");
//        rolePermissionService.showMenuAvaliable(a);
//        log.info("数据库刷新完毕");
//        return Result.success(loginer);
//    }
//        return Result.error("用户名或密码错误！");
//}
//}
