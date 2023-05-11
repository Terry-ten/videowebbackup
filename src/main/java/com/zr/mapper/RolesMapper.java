package com.zr.mapper;

import com.zr.pojo.RolePermission;
import com.zr.pojo.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【roles】的数据库操作Mapper
* @createDate 2023-04-03 12:36:36
* @Entity com.zr.pojo.Roles
*/
@Mapper
public interface RolesMapper extends BaseMapper<Roles> {

    @Select("select rolename from roles where id=#{roletype}")
    String selectByid(Integer roletype);

    @Select("select * from roles")
    List<Roles> selectAllRolePermission();

    @Insert("insert into roles(rolename, description) values(#{rolename},#{description}) ")
    void insertRole(Roles role);

    @Select("select * from roles")
    List<Roles> selectAllRoles();

    @Delete("delete from roles where rolename=#{rolename}")
    void deleteRoles(String rolename);

    @Select("select id from roles where rolename=#{rolename}")
    Integer selectIdByname(String rolename);

    @Select("select  rolename from roles where id =#{id}")
    Roles selectByPrimaryKey(Integer id);
}




