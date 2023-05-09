package com.zr.service;

import com.zr.pojo.PageBean;
import com.zr.pojo.Types;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【types】的数据库操作Service
* @createDate 2023-04-13 13:55:20
*/
public interface TypesService  {

    PageBean getAllType(Integer page, Integer pageSize);

    void addType(String typename);

    void deleteType(Integer id);

    void updateType(Integer id,String typename,String oldname);

    List<Types> getAsidetypes();
}
