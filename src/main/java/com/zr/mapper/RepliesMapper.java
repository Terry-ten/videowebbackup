package com.zr.mapper;

import com.zr.pojo.Replies;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【replies】的数据库操作Mapper
* @createDate 2023-04-03 12:36:25
* @Entity com.zr.pojo.Replies
**/


 @Mapper
public interface RepliesMapper extends BaseMapper<Replies> {

     @Delete("delete from replies where id=#{id}")
    void deletebyId(Integer id);

     @Delete("delete from replies where comment_id=#{commentId}")
    void deleteByCommentId(Integer commentId);

     @Select("select * from replies where comment_id=#{commentId}")
    List<Replies> selectByCommentId(Integer commentId);

     @Delete("delete from replies where repliar=#{username}")
    void deleteByRepliar(String username);

     @Insert("insert into replies (repliar, comment_id, content, createtime) values(#{repliar},#{commentId},#{content},#{createtime}) ")
    void insertCommentReply(Replies reply);

     @Update("update replies set repliar=#{username} where repliar=#{oldname}")
    void updateReplyRepliar(String username, String oldname);

//    @Delete("delete from replies where comment_id = #{id}")
//    void deleteByCommentsId(Integer id);
}




