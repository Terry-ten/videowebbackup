package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zr.mapper.VideosMapper;
import com.zr.pojo.UserVideoLike;
import com.zr.service.UserVideoLikeService;
import com.zr.mapper.UserVideoLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 曾瑞
* @description 针对表【user_video_like】的数据库操作Service实现
* @createDate 2023-05-10 20:21:11
*/
@Service
public class UserVideoLikeServiceImpl extends ServiceImpl<UserVideoLikeMapper, UserVideoLike>
    implements UserVideoLikeService{

    @Autowired
    private UserVideoLikeMapper userVideoLikeMapper;

    @Autowired
    private VideosMapper videosMapper;

    @Override
    public UserVideoLike getUserLike(Integer userid, Integer videoid) {
      return userVideoLikeMapper.selectAllByUserIdVideoId(userid,videoid);
    }

    @Transactional
    @Override
    public void updateUserLike(UserVideoLike userVideoLike) {
        if (userVideoLikeMapper.selectAllByUserIdVideoId(userVideoLike.getUserid(),userVideoLike.getVideoid())==null){
            if(userVideoLike.getIslike()==1){
            userVideoLikeMapper.insertUserLike(userVideoLike);
                videosMapper.upVideoLikecount(userVideoLike);
            }
        }else{
            if(userVideoLike.getIslike()==1){
                userVideoLikeMapper.updateUserLike(userVideoLike);
                videosMapper.upVideoLikecount(userVideoLike);

            }else {
                userVideoLikeMapper.updateUserLike(userVideoLike);
                videosMapper.downVideoLikecount(userVideoLike);
            }


        }
    }
}




