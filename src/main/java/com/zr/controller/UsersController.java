package com.zr.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.zr.pojo.Users;
import com.zr.service.PermissionService;
import com.zr.service.RolePermissionService;
import com.zr.service.UsersService;
import com.zr.utils.AliYunOssUtils;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import com.zr.pojo.PageBean;
import com.zr.service.impl.UsersServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class UsersController {


    @Autowired
    private UsersService usersService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/users")
    public Result selectAllUsers(HttpServletRequest request,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 String username,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        Integer roletype = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");
        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletype);
        if (roletype == null ||!PermissionUtil.isAccess(id,permissionids)) {
            return Result.error(403);
        }
        log.info("准备调用service层的方法");
   PageBean pageBean= usersService.page(page,pageSize,username,begin,end);
        log.info("已经执行完毕，准备返回值");
    return Result.success(pageBean);
    }
    @DeleteMapping("/users/delete/{username}")
    public Result deleteByadmin(@PathVariable String username){
        try {
            usersService.deleteByadmin(username);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败");
        }
    }
    @PostMapping("/users/add")
    public Result AddUser(@RequestBody Users user)  {
        int result=usersService.addUser(user);
        if(result==UsersServiceImpl.DUPLICATE){
            return Result.error("用户名重复");
        }else if(result==UsersServiceImpl.ERROR){
            return Result.error("注册失败，请稍后重试");
        }
        return Result.success();
    }

    @PostMapping("/users/update")
    public Result updateUser(MultipartFile headimage,String username,
                             String phonenumber,String introduction,Integer id,
                             String headimageurl) throws IOException {
        String imageurl=null;
        if(headimage!=null){
            AliYunOssUtils.deleteFileByUrl(headimageurl);
            imageurl=AliYunOssUtils.uploadUser(headimage);
        }else {
            imageurl=headimageurl;
        }
        AliYunOssUtils.deleteFileByUrl(headimageurl);
        int result=usersService.updateUser(id,username,phonenumber,introduction,imageurl);
        if(result==UsersServiceImpl.DUPLICATE){
            return Result.error("用户名重复");
        }else if(result==UsersServiceImpl.ERROR){
            return Result.error("修改用户失败");
        }
        return Result.success();
    }
    @PostMapping("/users/back/add")
    public Result addBackUser(String username, MultipartFile headimage, String password,
                             String idcard, String phonenumber, String introduction) throws IOException {

        String imageurl= AliYunOssUtils.uploadUser(headimage);
        int result=usersService.uploadUser(username, imageurl, password, idcard,phonenumber,introduction);
        if(result==UsersServiceImpl.DUPLICATE){
            return Result.error("用户名重复");
        }else if(result==UsersServiceImpl.ERROR){
            return Result.error("新增用户失败");
        }
        return Result.success();
    }
    @PutMapping("/users/back/update")
    public Result updateBackUser(String username, MultipartFile headimage, String password,
                              String phonenumber, String introduction,Integer id,String headimageurl) throws IOException {
        log.info(headimageurl);
        String imageurl=null;
        if(headimage!=null){
            AliYunOssUtils.deleteFileByUrl(headimageurl);
             imageurl=AliYunOssUtils.uploadUser(headimage);
        }else {
            imageurl=headimageurl;
        }
        int result=usersService.updateUsers(id,username, imageurl, password,phonenumber,introduction);
        if(result==UsersServiceImpl.DUPLICATE){
            return Result.error("用户名重复");
        }else if(result==UsersServiceImpl.ERROR){
            return Result.error("修改用户失败");
        }
        return Result.success();
    }
    @GetMapping("/users/get/{id}")
    public Result getOneUserById(@PathVariable Integer id){
      Users user= usersService.getOneUserById(id);
      return Result.success(user);
    }
    @GetMapping("/users/watch/get/{username}")
    public Result getOneUser(@PathVariable String username){
      Users users=usersService.getOneUser(username);
      return Result.success(users);
    }
    @PutMapping("/users/password/update/{password}/{id}")
    public Result updatePassword(@PathVariable String password,@PathVariable Integer id ){
        try {
            usersService.updateUserPasswordById(id,password);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改密码失败");
        }
    }
}
