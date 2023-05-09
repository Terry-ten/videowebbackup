package com.zr.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName menus
 */
@TableName(value ="menus")
@Data
public class Menus implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "menuname")
    private String menuname;

    /**
     * 
     */
    @TableField(value = "viewable")
    private Integer viewable;

    @TableField(value = "route")
    private String route;
    @TableField(value = "type")
    private String type;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}