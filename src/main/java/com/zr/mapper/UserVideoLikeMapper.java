package com.zr.mapper;

import com.zr.pojo.UserVideoLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author 曾瑞
* @description 针对表【user_video_like】的数据库操作Mapper
* @createDate 2023-05-10 20:21:11
* @Entity com.zr.pojo.UserVideoLike
*/
@Mapper
public interface UserVideoLikeMapper extends BaseMapper<UserVideoLike> {

    @Select("select * from user_video_like where userid=#{userid} and videoid=#{videoid}")
    UserVideoLike selectAllByUserIdVideoId(Integer userid,Integer videoid);

    @Insert("insert into user_video_like (userid, videoid, islike) values (#{userid},#{videoid},#{islike})")
    void insertUserLike(UserVideoLike userVideoLike);

    @Update("update user_video_like set islike = #{islike} where userid=#{userid} and videoid=#{videoid}")
    void updateUserLike(UserVideoLike userVideoLike);
}




