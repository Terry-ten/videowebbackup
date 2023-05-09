package com.zr.service;

import com.zr.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【role_permission】的数据库操作Service
* @createDate 2023-04-03 12:36:32
*/
public interface RolePermissionService {

    void showMenuAvaliable(Users u);
    PageBean page(Integer page, Integer pageSize);

    RolesReturn getSelectedPermission(Integer id);


    void updateRolePermissions(Integer roleId, List<Integer> permissionIds);


    List<Integer> selectPermissionId(Integer roletype);


}
