package com.zr.service;

import com.zr.pojo.Roles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zr.pojo.UserRole;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【roles】的数据库操作Service
* @createDate 2023-04-03 12:36:36
*/
public interface RolesService  {

    void insertRole(Roles role);

    List<Roles> getRoles();

    void deleteRoles(Integer userid,String rolename);
}
