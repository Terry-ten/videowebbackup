package com.zr.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName comments
 */
@TableName(value ="comments")
@Data
public class Comments implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "speaker")
    private String speaker;

    /**
     * 
     */
    @TableField(value = "videoid")
    private String videoid;

    /**
     * 
     */
    @TableField(value = "content")
    private String content;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "createtime")
    private LocalDateTime createtime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}