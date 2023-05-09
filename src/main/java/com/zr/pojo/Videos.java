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
 * @TableName videos
 */
@TableName(value ="videos")
@Data
public class Videos implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "author")
    private String author;

    /**
     * 
     */
    @TableField(value = "title")
    private String title;

    /**
     * 
     */
    @TableField(value = "description")
    private String description;

    /**
     * 
     */
    @TableField(value = "coverimage")
    private String coverimage;

    /**
     * 
     */
    @TableField(value = "videolocation")
    private String videolocation;

    /**
     * 
     */
    @TableField(value = "viewable")
    private Integer viewable;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "createtime")
    private LocalDateTime createtime;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "updatetime")
    private LocalDateTime updatetime;

    @TableField(value = "goods")
    private Integer goods;
    @TableField(value = "watchcount")
    private Integer watchcount;

    @TableField(value = "type")
    private String type;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}