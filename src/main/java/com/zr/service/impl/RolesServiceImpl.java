package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zr.anno.MyLogs;
import com.zr.mapper.RolePermissionMapper;
import com.zr.mapper.UserRoleMapper;
import com.zr.mapper.UsersMapper;
import com.zr.pojo.Roles;
import com.zr.pojo.UserRole;
import com.zr.service.RolePermissionService;
import com.zr.service.RolesService;
import com.zr.mapper.RolesMapper;
import com.zr.service.UserRoleService;
import com.zr.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【roles】的数据库操作Service实现
* @createDate 2023-04-03 12:36:36
*/
@Service
public class RolesServiceImpl
    implements RolesService{

    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UsersMapper usersMapper;





    @MyLogs
    @Transactional
    @Override
    public void insertRole(Roles role) {
        rolesMapper.insertRole(role);
        rolePermissionMapper.insertDefaultRolePermission(rolesMapper.selectIdByname(role.getRolename()),role.getRolename());
    }

    @Override
    public List<Roles> getRoles() {
       List<Roles> roles= rolesMapper.selectAllRoles();
        return roles;
    }

    @MyLogs
    @Transactional
    @Override
    public void deleteRoles(Integer userid,String rolename) {
        rolesMapper.deleteRoles(rolename);
        rolePermissionMapper.deleteRolePermissionsByRolname(rolename);
        UserRole userRole=new UserRole();
        userRole.setRolename("普通用户");
        userRole.setRoletype(3);
        userRole.setUserid(userid);
        userRoleMapper.updateRoleType(userRole);
        usersMapper.updateRoletype(userid,3);

    }
    public String getRoleNameByRoleId(int roleId) {
        Roles role = rolesMapper.selectByPrimaryKey(roleId);
        if (role != null) {
            return role.getRolename();
        }
        return null;
    }
}




