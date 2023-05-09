package com.zr.service;

import com.zr.pojo.PageBean;
import com.zr.pojo.Replies;

import java.util.List;

/**
* @author 曾瑞
* @description 针对表【replies】的数据库操作Service
* @createDate 2023-04-03 12:36:25
*/
public interface RepliesService {

    void deleteReplyById(Integer id);


    PageBean page(Integer page, Integer pageSize, Integer commentId);

    List<Replies> selectByCommentId(Integer commentid);



    void deleteReplyByRepliar(String username);

    void addCommentReply(Replies reply);

    void deleteReplyByCommentId(Integer id);

    void updateReplyRepliar(String username, String oldname);
}
