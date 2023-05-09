package com.zr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zr.pojo.DataBases;

import java.util.List;


/**
* @author 曾瑞
* @description 针对表【databases】的数据库操作Service
* @createDate 2023-04-25 15:28:23
*/
public interface DatabasesService {


    void uploadDataBase(String dataBase, String url);

    List<DataBases> getAllDataBase();

    void updateDatabase(String tablename, String url);
}
