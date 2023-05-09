package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zr.pojo.Permissions;
import com.zr.service.PermissionService;
import com.zr.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 曾瑞
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2023-04-03 12:36:22
*/
@Service
public class PermissionServiceImpl
    implements PermissionService{

    @Autowired
   private PermissionMapper permissionMapper;
    @Override
    public Integer selectPermissionIdByController(String controller) {
        Integer permissionId=permissionMapper.selectPermissionIdByController(controller);
        return permissionId;
    }
}




