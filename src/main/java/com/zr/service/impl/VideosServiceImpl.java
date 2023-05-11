package com.zr.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.zr.anno.MyLogs;
import com.zr.mapper.ReportsMapper;
import com.zr.pojo.PageBean;
import com.zr.pojo.Videos;
import com.zr.service.CommentsService;
import com.zr.service.VideosService;
import com.zr.mapper.VideosMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【videos】的数据库操作Service实现
* @createDate 2023-04-03 12:36:48
*/
@Slf4j
@Service
public class VideosServiceImpl
    implements VideosService{

    @Autowired
    private VideosMapper videosMapper;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private ReportsMapper reportsMapper;
    @Override
    public PageBean videospage(Integer page, Integer pagesize, String author, LocalDate begin,LocalDate end) {
        PageHelper.startPage(page,pagesize);

        List<Videos> videos =videosMapper.selectAllVideos(author,begin,end);
        Page<Videos> page1 = (Page<Videos>) videos;

        return new PageBean(page1.getTotal(),page1.getResult());
    }

    @Override
    public Videos getVideoById(Integer id) {
       Videos videos=videosMapper.selectVideoById(id);
        return videos;
    }

    @MyLogs
    @Override
    public void insertVideo(String author, String title, String description, String coverimageurl, String videourl, String type) {
        LocalDateTime createtime=LocalDateTime.now();
        Videos videos=new Videos();
        videos.setAuthor(author);
        videos.setTitle(title);
        videos.setDescription(description);
        videos.setCoverimage(coverimageurl);
        videos.setVideolocation(videourl);
        videos.setType(type);
        videos.setCreatetime(createtime);
        videos.setUpdatetime(createtime);
        videosMapper.insertVideo(videos);


    }

    @Override
    public List<Videos> getUserVideoByAuthor(String username) {
        List<Videos> videos=videosMapper.selectVideoByAuthor(username);
        return videos;
    }

    @Override
    public List<Videos> getMainVideos(String type, String sort, String keyword) {
        List<Videos> videos=videosMapper.selectMainVideos(type,sort,keyword);
        return videos;
    }

    @Override
    public List<Videos> getMyVideos(String username) {
        List<Videos> videos=videosMapper.selectVideoByAuthor(username);
        return videos;
    }



    @MyLogs
    @Override
    public void updateViewable(Videos videos) {
        videosMapper.updateViewable(videos);
    }

    @Override
    public List<Videos> getTypeVideos(String typename) {
        List<Videos> videos=videosMapper.selectVideoBytype(typename);
        return videos;
    }

    @MyLogs
    @Transactional
    @Override
    public void deleteVideoByAuthor(String username) {

        List<Videos> videos=videosMapper.selectVideoByAuthor(username);
        for (Videos video : videos) {
            videosMapper.deleteMyVideById(video.getId());
            commentsService.deleteCommentsByVideoId(video.getId());
        }

    }
    @MyLogs
    @Transactional
    @Override
    public void deleteVideoById(Integer id) {
        videosMapper.deleteMyVideById(id);
        commentsService.deleteCommentsByVideoId(id);
        reportsMapper.deleteByTargetId(id);
    }

    @MyLogs
    @Override
    public void updateVideo(Integer id, String title, String description, String coverimageurl) {
        videosMapper.updateVideo(id,title,description,coverimageurl);
    }

    @MyLogs
    @Override
    public void updateVideoAuthor(String username,String oldname) {
        videosMapper.updateVideoAuthor(username,oldname);
    }

    @Override
    public void updateWatchcount(Integer id) {
        videosMapper.updateWatchcount(id);
    }

    @Override
    public PageBean Mainpage(Integer page, Integer pageSize, String type, String sort, String keyword) {
        PageHelper.startPage(page,pageSize);
        List<Videos> videos = videosMapper.selectMainVideos(type,sort,keyword);
        Page<Videos> page1 = (Page<Videos>) videos;
        log.info("主页总页数为"+page1.getTotal());

        return new PageBean(page1.getTotal(),page1.getResult());
    }
}
