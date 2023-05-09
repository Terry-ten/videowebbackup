package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.anno.MyLogs;
import com.zr.mapper.MenusMapper;
import com.zr.mapper.PermissionMapper;
import com.zr.mapper.RolesMapper;
import com.zr.pojo.*;
import com.zr.service.RolePermissionService;
import com.zr.mapper.RolePermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【role_permission】的数据库操作Service实现
* @createDate 2023-04-03 12:36:32
*/
@Slf4j
@Service
public class RolePermissionServiceImpl
    implements RolePermissionService{


    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private MenusMapper menusMapper;
    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Transactional
    @Override
    public void showMenuAvaliable(Users u) {
        log.info("已经进入showMenuAvaliable，准备查找该用户具有的权限");
        List<Integer> ids=rolePermissionMapper.selectPermissions(u);
        log.info("查找完毕，该用户具有如下权限"+ids);
        menusMapper.updateAllViewable();
        menusMapper.updateZeroViewable(ids);
        log.info("已经修改完该用户的权限");
    }

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
//        List<RolePermission> rolePermissions = rolePermissionMapper.selectAllRolePermission();
        List<Roles> rolePermissions = rolesMapper.selectAllRolePermission();


        Page<Roles> rolePermissionsPage = (Page<Roles>) rolePermissions;
        return new PageBean(rolePermissionsPage.getTotal(), rolePermissionsPage.getResult());
    }

    @Override
    public RolesReturn getSelectedPermission(Integer id) {
        List<Permissions> selectedpermission=rolePermissionMapper.selectSelectedPermission(id);
        List<Permissions> rolePermissions = permissionMapper.selectAllRolePermission();
        RolesReturn rolesReturn=new RolesReturn(rolePermissions,selectedpermission);
        return rolesReturn;
    }

    @Transactional
    @MyLogs
    @Override
    public void updateRolePermissions(Integer roleId, List<Integer> permissionIds) {
        // 删除当前角色的所有权限关联
        rolePermissionMapper.deleteRolePermissions(roleId);
        // 重新添加权限关联
        for (Integer permissionId : permissionIds) {
           String rolename= rolesMapper.selectByid(roleId);
            String permissionname=permissionMapper.selectByid(permissionId);
            RolePermission rolePermission=new RolePermission(null,roleId,permissionId,rolename,permissionname);
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
    }

    @Override
    public List<Integer> selectPermissionId(Integer roletype) {
       List<Integer> permissionids= rolePermissionMapper.selectPermissionsByid(roletype);
        return permissionids;
    }
}




