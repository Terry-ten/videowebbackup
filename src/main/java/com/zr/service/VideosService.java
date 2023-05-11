package com.zr.service;

import com.zr.anno.MyLogs;
import com.zr.pojo.PageBean;
import com.zr.pojo.Videos;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【videos】的数据库操作Service
* @createDate 2023-04-03 12:36:48
*/
public interface VideosService  {

    PageBean videospage(Integer page, Integer pagesize, String author, LocalDate begin,LocalDate end);

    Videos getVideoById(Integer id);

    void insertVideo(String author, String title, String description, String coverimageurl, String videourl, String type);

    List<Videos> getUserVideoByAuthor(String username);



    List<Videos> getMainVideos(String type, String integer, String sort);

    List<Videos> getMyVideos(String username);


    void updateViewable(Videos videos);

    List<Videos> getTypeVideos(String typename);

    void deleteVideoByAuthor(String username);


    void deleteVideoById(Integer id);

    void updateVideo(Integer id, String title, String description, String coverimageurl);


    void updateVideoAuthor(String username,String oldname );

    void updateWatchcount(Integer id);

    PageBean Mainpage(Integer page, Integer pageSize, String type, String sort, String keyword);
}
