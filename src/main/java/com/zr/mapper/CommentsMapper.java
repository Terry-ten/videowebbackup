package com.zr.mapper;

import com.zr.pojo.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【comments】的数据库操作Mapper
* @createDate 2023-04-03 12:36:10
* @Entity com.zr.pojo.Comments
*/
@Mapper
public interface CommentsMapper  {

    @Insert("insert into comments (speaker,videoid,content,createtime) values (#{speaker},#{videoid},#{content},#{createtime})")
    void inSertComments(Comments comments);

    List<Comments> selectAllComments(String speaker, Integer videoid, LocalDate begin, LocalDate end);

    @Delete("delete from comments where id =#{id}")
    void deletebyId(Integer id);
    @Select("select * from comments where id = #{id}")
    Comments selectCommentById(Integer id);

    @Select("select * from comments where videoid=#{videoid}")
    List<Comments> selectCommentsByVideolocation(Integer videoid);

    @Delete("delete from comments where speaker=#{username}")
    void deleteBySpeaker(String username);

    @Select("select * from comments where videoid=#{id}")
    List<Comments> selectCommentByVideoId(Integer id);
    @Delete("delete from comments where videoid=#{id}")
    void deletebyVideoId(Integer id);

    @Select("select * from comments where speaker=#{username}")
    List<Comments> selectCommentBySpeaker(String username);

    @Update("update comments set speaker =#{username} where speaker=#{oldname} ")
    void updateCommentSpeaker(String username, String oldname);
}




