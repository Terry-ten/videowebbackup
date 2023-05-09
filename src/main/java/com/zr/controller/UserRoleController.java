package com.zr.controller;

import com.zr.pojo.PageBean;
import com.zr.pojo.UserRole;
import com.zr.service.PermissionService;
import com.zr.service.RolePermissionService;
import com.zr.service.UserRoleService;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/16 10:13
 */
@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/userrole")
    public Result getAllUserRole(HttpServletRequest request,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 String username, Integer userid){
        Integer roletype = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");
        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletype);
        if (roletype == null ||!PermissionUtil.isAccess(id,permissionids)) {
            // 用户没有权限访问此页面
            return Result.error(403);
        }
        PageBean pageBean=userRoleService.page(page,pageSize,username,userid);
        return Result.success(pageBean);
    }

    @PutMapping("/userrole/update")
    public Result updateRoleType(@RequestBody UserRole userRole){
        try {
            userRoleService.updateRoleType(userRole);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败");
        }
    }



}
