package com.zr.service;

import com.zr.pojo.Comments;
import com.zr.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【comments】的数据库操作Service
* @createDate 2023-04-03 12:36:11
*/
public interface CommentsService {

    public void inSertComments(Comments comments);

    PageBean page(Integer page, Integer pageSize, String speaker, Integer videolocation, LocalDate begin, LocalDate end);

    void deleteCommentsById(Integer id);

    Comments getCommentById(Integer id);

    List<Comments> getVideoComments(Integer videoid);

    void deleteCommentsBySpeaker(String username);

    void deleteCommentsByVideoId(Integer id);

    void updateCommentSpeaker(String username,String oldname);
}
