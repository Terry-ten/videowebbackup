package com.zr.mapper;

import com.zr.pojo.DataBases;
import com.zr.pojo.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 曾瑞
 * @description 针对表【users】的数据库操作Mapper
 * @createDate 2023-04-03 12:36:44
 * @Entity com.zr.pojo.Users
 */

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    @Select("select * from users where id =#{id}")
    public Users selectByIdUsers(Integer id);

//    @Select("select * from users ")
//    public List<Users> selectAllUsers();
    @Select("select username from users where id=#{id}")
    public String selectUsernameById(Integer id);
    public List<Users> selectAllUsers(String username, LocalDate begin, LocalDate end);


    @Select("select count(0) from users where username=#{username}")
    int selectByUsername(String username);
    void insertUsers(Users user);

    @Delete("delete from users where username=#{username}")
    void deleteByadmin(String username);


    void updateUsers(Users user);

    @Select("select * from users where username =#{username} and password=#{password}")
    Users selectUsernameAndPassword(Users user);



    @Update("update users set password = #{password} where username=#{username} and question=#{question} and answer=#{answer}")
    void updateUserPassword(Users users);

    @Update("update users set headimage = #{headimage}")
    void updateUserimage(String headimage);


    @Select("select  * from users where username =#{username}")
    Users selectAllByUsername(String username);

    @Select("select * from users where username like concat('%',#{username},'%')")
    List<Users> selectAllVideoUsers(String username);

    @Update("update users set roletype =#{roletype} where id=#{userid}")
    void updateRoletype(Integer userid, Integer roletype);

    @Select("select id from users where username=#{username}")
    Integer selectIdByname(String username);

    @Select("select question from users where username= #{username}")
    String selectQuestionByUsername(String username);

    @Update("update users set password =#{password} where id =#{id}")
    void updateUserPasswordById(Integer id, String password);
}