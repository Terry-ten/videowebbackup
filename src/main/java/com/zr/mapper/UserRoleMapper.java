package com.zr.mapper;

import com.zr.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2023-04-16 10:12:36
* @Entity com.zr.pojo.UserRole
*/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {



    List<UserRole> selectAllUserRole(String username, Integer userid);

    @Update("UPDATE user_role SET rolename = #{rolename}, roletype = #{roletype} WHERE userid = #{userid}")
    void updateRoleType(UserRole userRole);

    @Delete("delete from user_role where username=#{username}")
    void deleteByUsername(String username);

    @Insert("insert into user_role (userid, username, roletype, rolename) values(#{userid},#{username},3,'普通用户')")
    void insertUserRole(Integer userid, String username);
}




