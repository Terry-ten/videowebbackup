package com.zr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.anno.MyLogs;
import com.zr.pojo.PageBean;
import com.zr.pojo.Replies;
import com.zr.service.RepliesService;
import com.zr.mapper.RepliesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【replies】的数据库操作Service实现
* @createDate 2023-04-03 12:36:25
*/
@Service
public class RepliesServiceImpl
    implements RepliesService{

    @Autowired
    RepliesMapper repliesMapper;
    @Override
    public void deleteReplyById(Integer id) {

        try {
            repliesMapper.deletebyId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, Integer commentId) {
        PageHelper.startPage(page,pageSize);

        List<Replies> replies =repliesMapper.selectByCommentId(commentId);

        Page<Replies> page1= (Page<Replies>) replies;
        return new PageBean(page1.getTotal(),page1.getResult());
    }

    @Override
    public List<Replies> selectByCommentId(Integer commentid) {
      List<Replies> replies=  repliesMapper.selectByCommentId(commentid);
      return replies;
    }


    @Transactional
    @Override
    public void deleteReplyByRepliar(String username) {
        repliesMapper.deleteByRepliar(username);
    }

    @MyLogs
    @Override
    public void addCommentReply(Replies reply) {
        reply.setCreatetime(LocalDateTime.now());
        repliesMapper.insertCommentReply(reply);
    }

    @MyLogs
    @Override
    public void deleteReplyByCommentId(Integer id) {
        repliesMapper.deleteByCommentId(id);
    }

    @MyLogs
    @Override
    public void updateReplyRepliar(String username, String oldname) {
        repliesMapper.updateReplyRepliar(username,oldname);
    }
}




