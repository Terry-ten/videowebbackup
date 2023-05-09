package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zr.pojo.DataBases;
import com.zr.service.DatabasesService;
import com.zr.mapper.DatabasesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【databases】的数据库操作Service实现
* @createDate 2023-04-25 15:28:23
*/
@Service
public class DatabasesServiceImpl
    implements DatabasesService{
    @Autowired
    private DatabasesMapper databasesMapper;
    @Override
    public void uploadDataBase(String dataBase, String url) {
        LocalDateTime updatetime=LocalDateTime.now();
        databasesMapper.insertDataBase(dataBase,url,updatetime);
    }

    @Override
    public List<DataBases> getAllDataBase() {

        List<DataBases> dataBases=databasesMapper.getAllDataBase();
        return dataBases;
    }

    @Override
    public void updateDatabase(String tablename, String url) {
        LocalDateTime updatetime=LocalDateTime.now();
        databasesMapper.updateDatabase(tablename,url,updatetime);
    }
}




