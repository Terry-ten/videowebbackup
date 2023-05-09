package com.zr.mapper;

import com.zr.pojo.ReporstsPage;
import com.zr.pojo.Reports;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【reports】的数据库操作Mapper
* @createDate 2023-04-03 12:36:28
* @Entity com.zr.pojo.Reports
*/
@Mapper
public interface ReportsMapper extends BaseMapper<Reports> {


    List<ReporstsPage> selectAllReports(Integer type, LocalDate begin, LocalDate end);

    @Delete("delete from reports where user_id = #{userId}")
    void deleteByReporter(Integer userId);

    @Insert("insert into reports (user_id, type, targetid, reason, createtime) values (#{userId},#{type},#{targetid},#{reason},#{createtime}) ")
    void insertReport(Reports report);

    @Delete("delete from reports where id =#{id}")
    void deleteReportById(Integer id);

    @Delete("delete from reports where targetid = #{id}")
    void deleteByTargetId(Integer id);
}




