package com.zr.service;

import com.zr.pojo.UserVideoLike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 曾瑞
* @description 针对表【user_video_like】的数据库操作Service
* @createDate 2023-05-10 20:21:11
*/

public interface UserVideoLikeService extends IService<UserVideoLike> {



    UserVideoLike getUserLike(Integer userid, Integer videoid);

    void updateUserLike(UserVideoLike userVideoLike);
}
