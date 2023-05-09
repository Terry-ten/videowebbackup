package com.zr.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName role_permission
 */
@TableName(value ="role_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 
     */
    @TableField(value = "permission_id")
    private Integer permissionId;

    @TableField(value = "rolename")
    private String rolename;

    @TableField(value = "permissionname")
    private String permissionname;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}