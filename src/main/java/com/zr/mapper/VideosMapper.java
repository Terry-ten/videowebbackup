package com.zr.mapper;

import com.zr.pojo.UserVideoLike;
import com.zr.pojo.Videos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【videos】的数据库操作Mapper
* @createDate 2023-04-03 12:36:48
* @Entity com.zr.pojo.Videos
*/
@Mapper
public interface VideosMapper extends BaseMapper<Videos> {


    List<Videos> selectAllVideos(String author, LocalDate begin,LocalDate end);

    @Select("select * from videos where id = #{id}")
    Videos selectVideoById(Integer id);

    void insertVideo(Videos videos);

    @Select("select * from videos where author=#{author}")
    List<Videos> selectVideoByAuthor(String author);

    List<Videos> selectMainVideos(String type, String sort, String keyword);

    @Delete("delete from videos where id=#{id}")
    void deleteMyVideById(Integer id);

    @Delete("delete from videos where author=#{username}")
    void deleteByAuthor(String username);

    @Update("update videos set viewable =#{viewable} where id =#{id}")
    void updateViewable(Videos videos);

    @Select("select * from videos where type=#{typename}")
    List<Videos> selectVideoBytype(String typename);

    @Update("update videos set title=#{title},description=#{description},coverimage=#{coverimageurl} where id=#{id}")
    void updateVideo(Integer id, String title, String description, String coverimageurl);

    @Update("update videos set author = #{username} where author=#{oldname} ")
    void updateVideoAuthor(String username,String oldname);

    @Update("update videos set watchcount =watchcount+1 where id =#{id}")
    void updateWatchcount(Integer id);


    @Update("update videos set type = null where type=#{typename}")
    void updateVideoTypeNull(String typename);

    @Update("update videos set type = #{typename} where type=#{oldname}")
    void updateVideoType(String typename,String oldname);

    @Update("update videos set goods=goods+1 where id=#{videoid}")
    void upVideoLikecount(UserVideoLike userVideoLike);

    @Update("update videos set goods=goods-1 where id=#{videoid}")
    void downVideoLikecount(UserVideoLike userVideoLike);
}




