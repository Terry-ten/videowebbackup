package com.zr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.anno.MyLogs;
import com.zr.mapper.RepliesMapper;
import com.zr.mapper.ReportsMapper;
import com.zr.pojo.Comments;
import com.zr.pojo.PageBean;
import com.zr.service.CommentsService;
import com.zr.mapper.CommentsMapper;
import com.zr.service.RepliesService;
import com.zr.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【comments】的数据库操作Service实现
* @createDate 2023-04-03 12:36:11
*/
@Service
public class CommentsServiceImpl
    implements CommentsService{

    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private RepliesMapper repliesMapper;

    @Autowired
    private RepliesService repliesService;
    @Autowired
    private ReportsMapper reportsMapper;
    @Override
    public void inSertComments(Comments comments) {
        comments.setCreatetime(LocalDateTime.now());
        commentsMapper.inSertComments(comments);

    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String speaker, Integer videoid, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);

        List<Comments> comments=commentsMapper.selectAllComments(speaker,videoid,begin,end);
        Page<Comments> page1=(Page<Comments>) comments;
        return new PageBean(page1.getTotal(),page1.getResult());
    }

    @MyLogs
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCommentsById(Integer id) {

        try {
            commentsMapper.deletebyId(id);
            repliesMapper.deleteByCommentId(id);
            reportsMapper.deleteByTargetId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Comments getCommentById(Integer id) {
        Comments comments=commentsMapper.selectCommentById(id);
        return comments;
    }

    @Override
    public List<Comments> getVideoComments(Integer videoid) {
        List<Comments> comments=commentsMapper.selectCommentsByVideolocation(videoid);
        return comments;
    }

    @Transactional
    @Override
    public void deleteCommentsBySpeaker(String username) {
        commentsMapper.deleteBySpeaker(username);
        List<Comments> comments=commentsMapper.selectCommentBySpeaker(username);
        for (Comments comment : comments) {
            repliesService.deleteReplyByRepliar(comment.getSpeaker());
        }
    }

    @Transactional
    @Override
    public void deleteCommentsByVideoId(Integer id) {
        List<Comments> comments =commentsMapper.selectCommentByVideoId(id);

        commentsMapper.deletebyVideoId(id);
        for (Comments comment : comments) {
            repliesService.deleteReplyByCommentId(comment.getId());
            reportsMapper.deleteByTargetId(comment.getId());
        }
    }

    @MyLogs
    @Override
    public void updateCommentSpeaker(String username, String oldname) {
        commentsMapper.updateCommentSpeaker(username,oldname);
    }
}




