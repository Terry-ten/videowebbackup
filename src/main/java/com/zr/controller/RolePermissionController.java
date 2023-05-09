package com.zr.controller;

import com.zr.pojo.PageBean;
import com.zr.pojo.RolesReturn;
import com.zr.pojo.RolePermissionRequest;
import com.zr.service.PermissionService;
import com.zr.service.RolePermissionService;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/16 10:54
 */
@RestController
public class RolePermissionController {


    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;

//        @GetMapping("/rolepermission")
//        public Result getAllRolePermission(@RequestParam(defaultValue = "1") Integer page,
//                                           @RequestParam(defaultValue = "10") Integer pageSize) {
//            PageBean pageBean = rolePermissionService.page(page, pageSize);
//            return Result.success(pageBean);
//        }
    @GetMapping("/rolepermission")
    public Result getAllRolePermission(HttpServletRequest request,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        Integer roletype = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");
        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletype);
        if (roletype == null ||!PermissionUtil.isAccess(id,permissionids)) {
            // 用户没有权限访问此页面
            return Result.error(403);
        }
        PageBean pageBean = rolePermissionService.page(page, pageSize);
        return Result.success(pageBean);
    }

    @GetMapping("/rolepermission/{id}")
    public Result getSelectedPermission(@PathVariable Integer id){
        RolesReturn rolesReturn=rolePermissionService.getSelectedPermission(id);
        return Result.success(rolesReturn);
    }
    @PutMapping("/rolepermission/update")
    public Result updateRolePermissions(@RequestBody RolePermissionRequest request) {
        try {
            rolePermissionService.updateRolePermissions(request.getRoleId(), request.getPermissionIds());
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败");
        }
    }
}
