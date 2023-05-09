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
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "username")
    private String username;

    /**
     * 
     */
    @TableField(value = "password")
    private String password;

    /**
     * 
     */
    @TableField(value = "idcard")
    private String idcard;

    /**
     * 
     */
    @TableField(value = "phonenumber")
    private String phonenumber;

    /**
     * 
     */
    @TableField(value = "headimage")
    private String headimage;

    /**
     * 
     */
    @TableField(value = "introduction")
    private String introduction;

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

    @TableField(value = "question")
    private String question;
    @TableField(value = "answer")
    private String answer;
    @TableField(value = "roletype")
    private Integer roletype;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

//    public Users(String username, String headimage, String password, String idcard, String phonenumber, LocalDateTime createtime, LocalDateTime updatetime) {
//    }
}