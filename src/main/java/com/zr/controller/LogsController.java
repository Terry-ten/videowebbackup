package com.zr.controller;

import com.zr.pojo.Ids;
import com.zr.service.LogsService;
import com.zr.pojo.PageBean;
import com.zr.service.PermissionService;
import com.zr.service.RolePermissionService;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author zr
 * @date 2023/4/18 21:11
 */
@RestController
public class LogsController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private LogsService logsService;
    @GetMapping("/logs")
    public Result selectAllUsers(HttpServletRequest request,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 Integer oprateId,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        Integer roletyp = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");

        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletyp);
        if (roletyp == null ||!PermissionUtil.isAccess(id,permissionids)) {
            // 用户没有权限访问此页面
            return Result.error(403);
        }

        PageBean pageBean= logsService.page(page,pageSize,oprateId,begin,end);

        return Result.success(pageBean);
    }
    @DeleteMapping("/logs/delete")
    public Result deleteLogs(@RequestBody Ids ids){
        try {
            List<Integer> list =ids.getIds();
            logsService.deleteLogs(list);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！");
        }
    }
}
