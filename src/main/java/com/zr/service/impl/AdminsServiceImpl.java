package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zr.pojo.Admins;
import com.zr.pojo.Users;
import com.zr.service.AdminsService;
import com.zr.mapper.AdminsMapper;
import com.zr.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 曾瑞
* @description 针对表【admins】的数据库操作Service实现
* @createDate 2023-04-03 12:36:06
*/
@Service
public class AdminsServiceImpl
    implements AdminsService{


    @Autowired
    private AdminsMapper adminsMapper;
    @Override
    public Admins adminLogin(Admins admin) {
        admin.setPassword(MD5Util.md5(admin.getPassword()));
        Admins a = adminsMapper.selectUsernameAndPassword(admin);
        return a;
    }
}




