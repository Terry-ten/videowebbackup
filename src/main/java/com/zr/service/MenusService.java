package com.zr.service;

import com.zr.pojo.Menus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zr.pojo.PageBean;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【menus】的数据库操作Service
* @createDate 2023-04-15 14:07:17
*/
public interface MenusService {

    PageBean page(Integer page, Integer pageSize, String menuname);

    void updateViewable(Integer id, Integer viewable);

    List<Menus> SelectAllMenus();
}
