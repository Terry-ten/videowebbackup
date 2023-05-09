package com.zr.mapper;

import com.zr.pojo.Menus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【menus】的数据库操作Mapper
* @createDate 2023-04-15 14:07:17
* @Entity com.zr.pojo.Menus
*/
@Mapper
public interface MenusMapper extends BaseMapper<Menus> {

    List<Menus> selectAllMenu(String menuname);

    @Update("update menus set viewable=#{viewable} where id =#{id}")
    void updateViewable(Integer id, Integer viewable);


    void updateZeroViewable(List<Integer> ids);

    @Update("update menus set viewable =1")
    void updateAllViewable();

    @Select("select * from menus")
    List<Menus> selectAllMenus();
}




