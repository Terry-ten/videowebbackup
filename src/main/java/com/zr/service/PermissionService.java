package com.zr.service;

import com.zr.pojo.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 曾瑞
* @description 针对表【permission】的数据库操作Service
* @createDate 2023-04-03 12:36:22
*/
public interface PermissionService {

    Integer selectPermissionIdByController(String substring);
}
