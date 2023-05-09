package com.zr.mapper;

import com.zr.pojo.Types;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【types】的数据库操作Mapper
* @createDate 2023-04-13 13:55:20
* @Entity com.zr.pojo.Types
*/
@Mapper
public interface TypesMapper extends BaseMapper<Types> {
    @Select("select * from types order by id")
    List<Types> selectAllTypes();

    @Insert("insert into types (typename) values (#{typename})")
    void insertType(String typename);

    @Delete("delete from types where id =#{id}")
    void deleteType(Integer id);

    @Update("update types set typename=#{typename} where id=#{id}")
    void updateType(Integer id,String typename);

    @Select("select * from types")
    List<Types> selectAsideTypes();

    @Select("select typename from types where id=#{typename}")
    String selectTypeById(Integer id);
}




