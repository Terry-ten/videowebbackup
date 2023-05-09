package com.zr.mapper;

import com.zr.pojo.Admins;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author 曾瑞
* @description 针对表【admins】的数据库操作Mapper
* @createDate 2023-04-03 12:36:06
* @Entity com.zr.pojo.Admins
*/
@Mapper
public interface AdminsMapper extends BaseMapper<Admins> {
    @Select("select * from admins where username =#{username} and password=#{password} ")
    Admins selectUsernameAndPassword(Admins admin);
}




