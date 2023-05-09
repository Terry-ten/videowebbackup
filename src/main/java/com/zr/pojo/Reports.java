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
 * @TableName reports
 */
@TableName(value ="reports")
@Data
public class Reports implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 
     */
    @TableField(value = "targetid")
    private Integer targetid;

    /**
     * 
     */
    @TableField(value = "reason")
    private String reason;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "createtime")
    private LocalDateTime createtime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}