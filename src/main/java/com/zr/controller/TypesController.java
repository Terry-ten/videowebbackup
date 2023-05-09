package com.zr.controller;

import com.zr.pojo.PageBean;
import com.zr.pojo.Types;
import com.zr.service.PermissionService;
import com.zr.service.RolePermissionService;
import com.zr.service.TypesService;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/13 13:44
 */
@RestController
public class TypesController {

    @Autowired
    TypesService typesService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/types")
    public Result getAllTypes(HttpServletRequest request,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize){
        Integer roletype = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");
        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletype);
        if (roletype == null ||!PermissionUtil.isAccess(id,permissionids)) {
            // 用户没有权限访问此页面
            return Result.error(403);
        }
        PageBean pageBean=typesService.getAllType(page,pageSize);
        return Result.success(pageBean);
    }
    @PostMapping ("/types/add/{typename}")
    public Result addType(@PathVariable String typename){
        try {
            typesService.addType(typename);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("添加分类失败，请检查");
        }
    }
    @DeleteMapping("/types/delete/{id}")
    public Result deleteType(@PathVariable Integer id){
        try {
            typesService.deleteType(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除分类失败");
        }
    }
    @PutMapping ("/types/update")
    public Result updateType(@RequestParam Integer id,@RequestParam String typename,@RequestParam String oldname){
        try {
            typesService.updateType(id,typename,oldname);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改分类失败，请检查");
        }
    }
    @GetMapping("/types/all")
    public Result getAsideTypes(){
        List<Types> types =typesService.getAsidetypes();
        return Result.success(types);
    }
}
