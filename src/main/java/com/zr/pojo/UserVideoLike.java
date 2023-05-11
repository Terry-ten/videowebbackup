package com.zr.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_video_like
 */
@TableName(value ="user_video_like")
@Data
public class UserVideoLike implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "userid")
    private Integer userid;

    /**
     * 
     */
    @TableField(value = "videoid")
    private Integer videoid;

    /**
     * 
     */
    @TableField(value = "islike")
    private Integer islike;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}