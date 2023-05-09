package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.mapper.PermissionMapper;
import com.zr.pojo.Menus;
import com.zr.pojo.PageBean;
import com.zr.service.MenusService;
import com.zr.mapper.MenusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【menus】的数据库操作Service实现
* @createDate 2023-04-15 14:07:17
*/
@Service
public class MenusServiceImpl
    implements MenusService{


    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MenusMapper menusMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String menuname) {
        PageHelper.startPage(page,pageSize);

        List<Menus> menus=menusMapper.selectAllMenu(menuname);
        Page<Menus> menus1= (Page<Menus>) menus;
        return new PageBean(menus1.getTotal(),menus1.getResult());
    }

    @Transactional
    @Override
    public void updateViewable(Integer id, Integer viewable) {
        menusMapper.updateViewable(id,viewable);
        permissionMapper.updateAvailable(id,viewable);
    }

    @Override
    public List<Menus> SelectAllMenus() {
        List<Menus> menus=menusMapper.selectAllMenus();
        return menus;
    }
}




