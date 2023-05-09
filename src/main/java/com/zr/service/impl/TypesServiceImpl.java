package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.anno.MyLogs;
import com.zr.mapper.VideosMapper;
import com.zr.pojo.PageBean;
import com.zr.pojo.Types;
import com.zr.service.TypesService;
import com.zr.mapper.TypesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【types】的数据库操作Service实现
* @createDate 2023-04-13 13:55:20
*/
@Service
public class TypesServiceImpl
    implements TypesService{

    @Autowired
    private TypesMapper typeMapper;

    @Autowired
    private VideosMapper videosMapper;

    @Override
    public PageBean getAllType(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Types> types=typeMapper.selectAllTypes();
        Page<Types> types1= (Page<Types>) types;

        return new PageBean(types1.getTotal(),types1.getResult());
    }

    @Override
    public void addType(String typename) {
        typeMapper.insertType(typename);

    }

    @Transactional
    @MyLogs
    @Override
    public void deleteType(Integer id) {
        String typename=typeMapper.selectTypeById(id);
        typeMapper.deleteType(id);
        videosMapper.updateVideoTypeNull(typename);
    }
    @Transactional
    @MyLogs
    @Override
    public void updateType(Integer id,String typename,String oldname) {

        typeMapper.updateType(id,typename);
        videosMapper.updateVideoType(typename,oldname);
    }

    @Override
    public List<Types> getAsidetypes() {
       List<Types> types=typeMapper.selectAsideTypes();
       return types;
    }
}




