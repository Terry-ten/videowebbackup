package com.zr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/16 14:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesReturn {
    private List<Permissions> allPermissions;
    private List<Permissions> selectedPermissions;
}
