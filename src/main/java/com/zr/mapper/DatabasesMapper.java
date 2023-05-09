package com.zr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zr.pojo.DataBases;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【databases】的数据库操作Mapper
* @createDate 2023-04-25 15:28:23
* @Entity com.zr.pojo.Databases
*/
@Mapper
public interface DatabasesMapper extends BaseMapper<DataBases> {


    @Insert("insert into videoweb.databases(tablename,url,updatetime) values(#{database},#{url},#{updatetime})")
    void insertDataBase(String database, String url, LocalDateTime updatetime);

    @Select("select * from videoweb.databases")
    List<DataBases> getAllDataBase();

    @Update("update videoweb.databases set url=#{url},updatetime=#{updatetime} where tablename=#{tablename}")
    void updateDatabase(String tablename, String url,LocalDateTime updatetime);
}




