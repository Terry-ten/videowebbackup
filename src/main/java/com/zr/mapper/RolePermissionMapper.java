package com.zr.mapper;

import com.zr.pojo.Admins;
import com.zr.pojo.Permissions;
import com.zr.pojo.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zr.pojo.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【role_permission】的数据库操作Mapper
* @createDate 2023-04-03 12:36:32
* @Entity com.zr.pojo.RolePermission
*/
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    @Select("select permission_id from role_permission where role_id = #{roletype}")
    List<Integer> selectPermissions(Users u);

    @Select("select * from role_permission")
    List<RolePermission> selectAllRolePermission();

    @Select("select * from permissions where id in (select permission_id from role_permission where role_id=#{id})")
    List<Permissions> selectSelectedPermission(Integer id);

    @Select("DELETE FROM role_permission WHERE role_id = #{roleId}")
    void deleteRolePermissions(Integer roleId);


    @Insert("insert into role_permission( role_id, permission_id, permissionname, rolename) values (#{roleId},#{permissionId},#{permissionname},#{rolename})")
    void insertRolePermission(RolePermission rolePermission);

    @Select("select p.id from permissions p, role_permission rp where p.id = rp.permission_id and p.available = 1 and rp.role_id = #{id}")
    List<Integer> selectPermissionsByid(Integer id);

    @Delete("delete from role_permission where rolename=#{rolename}")
    void deleteRolePermissionsByRolname(String rolename);

    @Insert("insert into role_permission (role_id, permission_id, permissionname, rolename) values (#{roleid},10,'管理员主页',#{rolename})")
    void insertDefaultRolePermission(Integer roleid, String rolename);
}




