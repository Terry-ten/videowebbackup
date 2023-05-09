package com.zr.controller;

import com.zr.pojo.PageBean;
import com.zr.pojo.Permissions;
import com.zr.pojo.Reports;
import com.zr.service.PermissionService;
import com.zr.service.ReportsService;
import com.zr.service.RolePermissionService;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author zr
 * @date 2023/4/9 10:08
 */
@RestController
public class ReportsController {
    @Autowired
    private ReportsService reportsService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/reports")
    public Result getAllReports(HttpServletRequest request,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(defaultValue = "1") Integer type,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        Integer roletype = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");
        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletype);
        if (roletype == null ||!PermissionUtil.isAccess(id,permissionids)) {
            // 用户没有权限访问此页面
            return Result.error(403);
        }
        PageBean pageBean = null;
        try {
            pageBean = reportsService.page(page,pageSize,type,begin,end);
            return Result.success(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("获取失败");
        }


    }
    @PostMapping("/report/video/add")
    public Result addReportVideo(@RequestBody Reports report){
        try {

            reportsService.addReport(report);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败");
        }
    }
    @PostMapping("/report/comment/add")
    public Result addReportComment(@RequestBody Reports report){
        try {
            reportsService.addReport(report);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败");
        }
    }
    @DeleteMapping("/reports/delete/{id}")
    public Result deleteReport(@PathVariable Integer id){
        try {
            reportsService.deleteReportsByid(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败");
        }
    }
}
