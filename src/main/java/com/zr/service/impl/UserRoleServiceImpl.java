package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.anno.MyLogs;
import com.zr.mapper.RolesMapper;
import com.zr.mapper.UsersMapper;
import com.zr.pojo.PageBean;
import com.zr.pojo.UserRole;
import com.zr.service.UserRoleService;
import com.zr.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2023-04-16 10:12:36
*/
@Slf4j
@Service
public class UserRoleServiceImpl
    implements UserRoleService{

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String username, Integer userid) {
        PageHelper.startPage(page,pageSize);
        List<UserRole> userRoles=userRoleMapper.selectAllUserRole(username,userid);
        log.info("userRole的list："+userRoles.toString());
        Page<UserRole> userRoles1= (Page<UserRole>) userRoles;
        return new PageBean(userRoles1.getTotal(),userRoles1.getResult());
    }

    @Transactional
    @MyLogs
    @Override
    public void updateRoleType(UserRole userRole) {

        userRole.setRolename(rolesMapper.selectByid(userRole.getRoletype()));

        userRoleMapper.updateRoleType(userRole);
        usersMapper.updateRoletype(userRole.getUserid(),userRole.getRoletype());
    }

    @Transactional
    @Override
    public void deleteUserRoleByUsername(String username) {
        userRoleMapper.deleteByUsername(username);
    }
}




