package com.zr.service;

import com.zr.pojo.PageBean;
import com.zr.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 曾瑞
* @description 针对表【user_role】的数据库操作Service
* @createDate 2023-04-16 10:12:36
*/
public interface UserRoleService {

    PageBean page(Integer page, Integer pageSize, String username, Integer userid);

    void updateRoleType(UserRole userRole);

    void deleteUserRoleByUsername(String username);
}
