package com.zr.mapper;

import com.zr.pojo.Logs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【logs】的数据库操作Mapper
* @createDate 2023-04-15 10:44:10
* @Entity com.zr.pojo.Logs
*/
@Mapper
public interface LogsMapper extends BaseMapper<Logs> {


    @Insert("INSERT INTO logs (operateid,roletype, createtime, classname, methodname, methodparams, returnvalue) VALUES (#{operateid},#{roletype} ,#{createtime}, #{classname}, #{methodname}, #{methodparams}, #{returnvalue})")
    void insertLogs(Logs log);

    List<Logs> selectPageLogs(Integer operateid, LocalDate begin, LocalDate end);
}




