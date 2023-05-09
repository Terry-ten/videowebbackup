package com.zr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/16 16:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionRequest {
    private Integer roleId;
    private List<Integer> permissionIds;

}