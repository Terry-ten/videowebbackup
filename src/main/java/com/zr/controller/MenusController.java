package com.zr.controller;

import com.zr.pojo.Menus;
import com.zr.pojo.PageBean;
import com.zr.service.MenusService;
import com.zr.service.PermissionService;
import com.zr.service.RolePermissionService;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/15 14:41
 */
@Slf4j
@RestController
public class MenusController {

    @Autowired
    private MenusService menusService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/menus")
    public Result getAllMenus(HttpServletRequest request,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              String menuname){
        Integer roletype = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");
        System.out.println(arr[arr.length - 1]);
        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletype);
        if (roletype == null ||!PermissionUtil.isAccess(id,permissionids)) {
            // 用户没有权限访问此页面
            return Result.error(403);
        }

        PageBean pageBean=menusService.page(page,pageSize,menuname);

        return Result.success(pageBean);
    }

    @PostMapping("/menus/update")
    public Result updateViewable(@RequestParam Integer id, @RequestParam Integer viewable){
        try {
            menusService.updateViewable(id,viewable);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新失败");
        }
    }
    @GetMapping("/menus/aside")
    public Result chekValid(HttpServletRequest request){
//        Integer roletype = (Integer) request.getAttribute("roletype");
//        log.info("该用户的角色为"+roletype);
//        String[] arr=this.getClass().getName().split("\\.");
//        System.out.println(arr[arr.length - 1]);
//        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
//        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletype);
//        if (roletype == null ||!PermissionUtil.isAccess(id,permissionids)) {
//            // 用户没有权限访问此页面
//
//            return Result.error(403);
//        }

        List<Menus> menus=menusService.SelectAllMenus();

        return Result.success(menus);
    }
}
