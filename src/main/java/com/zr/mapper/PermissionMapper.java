package com.zr.mapper;

import com.zr.pojo.Permissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2023-04-03 12:36:22
* @Entity com.zr.pojo.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permissions> {

    @Select("select * from permissions")
    List<Permissions> selectAllRolePermission();

    @Select("select permissionname from permissions where id =#{permissionId}")
    String selectByid(Integer permissionId);

    @Select("select id from permissions where controller like concat('%',#{controller},'%')")
    Integer selectPermissionIdByController(String controller);

    @Update("update permissions set available =#{viewable} where id =#{id}")
    void updateAvailable(Integer id, Integer viewable);
}




