package com.zr.controller;

import com.zr.pojo.Roles;
import com.zr.pojo.UserRole;
import com.zr.service.RolesService;
import com.zr.utils.Result;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/16 12:54
 */
@RestController
public class RoleController {


    @Autowired
    private RolesService rolesService;
    @PostMapping("/roles/add")
    public Result insertRole(@RequestBody Roles role){
        try {
            rolesService.insertRole(role);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("新增角色失败");
        }
    }
    @GetMapping("/roles/get")
    public Result getRoles(){
      List<Roles> roles=rolesService.getRoles();
      return Result.success(roles);
    }
    @DeleteMapping("/roles/delete")
    public Result deleteRoles(@RequestParam Integer userid,
                              @RequestParam  String rolename){
        try {
            rolesService.deleteRoles(userid,rolename);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败");
        }
    }

}
