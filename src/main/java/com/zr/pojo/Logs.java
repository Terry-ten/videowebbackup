package com.zr.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName logs
 */
@TableName(value ="logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logs implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作人ID
     */
    @TableField(value = "operateid")
    private Integer operateid;

    @TableField(value = "roletype")
    private String roletype;
    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "createtime")
    private LocalDateTime createtime;

    /**
     * 操作的类名
     */
    @TableField(value = "classname")
    private String classname;

    /**
     * 操作的方法名
     */
    @TableField(value = "methodname")
    private String methodname;

    /**
     * 方法参数
     */
    @TableField(value = "methodparams")
    private String methodparams;

    /**
     * 返回值
     */
    @TableField(value = "returnvalue")
    private String returnvalue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}