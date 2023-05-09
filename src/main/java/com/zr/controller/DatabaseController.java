package com.zr.controller;

import com.zr.pojo.DataBases;
import com.zr.service.*;
import com.zr.utils.AliYunOssUtils;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author zr
 * @date 2023/4/7 13:21
 */
@Slf4j
@RestController
public class DatabaseController {

    @Autowired
    private DatabasesService databasesService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @PostMapping("/databases/upload")
    public Result uploadDataBase(@RequestParam String url,@RequestParam MultipartFile tablename) throws IOException {
        log.info("tableurl"+url);
        if(url.equals("notexists")){
            String database=tablename.getOriginalFilename();
            log.info(database);
            String tablenameurl =AliYunOssUtils.uploadDataBase(tablename);
            try {
                databasesService.uploadDataBase(database,tablenameurl);
                return Result.success();
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("操作失败");
            }
        }else {
            AliYunOssUtils.deleteFileByUrl(url);
            String database=tablename.getOriginalFilename();
            log.info(database);
            String tablenameurl =AliYunOssUtils.uploadDataBase(tablename);
            try {
                databasesService.updateDatabase(database,tablenameurl);
                return Result.success();
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("操作失败");
            }
        }
    }
    @GetMapping("/databases")

    public Result getAllDataBase(HttpServletRequest request){
        Integer roletyp = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");

        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletyp);
        if (roletyp == null ||!PermissionUtil.isAccess(id,permissionids)) {
            // 用户没有权限访问此页面
            return Result.error(403);
        }
        List<DataBases> dataBases=databasesService.getAllDataBase();
        return Result.success(dataBases);
    }
}
