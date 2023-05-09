package com.zr.service;

import com.zr.pojo.Admins;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 曾瑞
* @description 针对表【admins】的数据库操作Service
* @createDate 2023-04-03 12:36:06
*/
public interface AdminsService{

    Admins adminLogin(Admins admin);
}
