package com.zr.controller;


import com.zr.pojo.Users;
import com.zr.pojo.Videos;
import com.zr.service.PermissionService;
import com.zr.service.RolePermissionService;
import com.zr.service.UsersService;
import com.zr.service.VideosService;
import com.zr.utils.AliYunOssUtils;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import com.zr.pojo.PageBean;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/5 19:48
 */
@Slf4j
@RestController
public class VideosController {
    @Autowired
    private VideosService videosService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/videos")
    public Result GetAllVideos(HttpServletRequest request,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               String author,
                               @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        Integer roletype = (Integer) request.getAttribute("roletype");
        String[] arr=this.getClass().getName().split("\\.");

        Integer id =permissionService.selectPermissionIdByController(arr[arr.length - 1]);
        List<Integer> permissionids= rolePermissionService.selectPermissionId(roletype);
        if (roletype == null ||!PermissionUtil.isAccess(id,permissionids)) {
            // 用户没有权限访问此页面
            return Result.error(403);
        }
        PageBean pageBean= videosService.videospage(page,pageSize,author,begin,end);

        return Result.success(pageBean);
    }
    @GetMapping("/videos/{targetid}")
    public Result getVideoByPathId(@PathVariable Integer id){
        Videos videos =videosService.getVideoById(id);

        return Result.success(videos);
    }

    @GetMapping("/videos/main")
    public Result getMainVideos(String type,String sort,String keyword){
        List<Videos> videos = videosService.getMainVideos(type,sort,keyword);
        return Result.success(videos);
    }
    @GetMapping("/videos/watch/{id}")
    public Result watchVideoById(@PathVariable Integer id){
        Videos videos =videosService.getVideoById(id);
        return Result.success(videos);
    }
    @GetMapping("/videos/users/{username}")
    public Result getMyVideos(@PathVariable String username){
        List<Videos> videos=videosService.getMyVideos(username);
        return Result.success(videos);
    }
    @PutMapping("/videos/update/viewable")
    public Result updateViewable(@RequestBody Videos videos){
        try {
            if(videos.getViewable()==1){
                videos.setViewable(0);
            }else {
                videos.setViewable(1);
            }
            videosService.updateViewable(videos);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败");
        }

    }
    /**
     * 该接口尚未进行事务管理
     * @param id
     * @return
     */
    @DeleteMapping("/videos/delete/{id}")
    public Result deleteMyVideo(@PathVariable Integer id){
        try {
            videosService.deleteVideoById(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败！");
        }
    }
    @PostMapping("/videos/add")
    public Result UploadUser(String author, String title, String description, MultipartFile coverimage, MultipartFile videolocation, String type) throws IOException {
        String coverimageurl= AliYunOssUtils.uploadVideo(coverimage);
        String videourl=AliYunOssUtils.uploadVideo(videolocation);
        try {
            videosService.insertVideo(author,title,description,coverimageurl,videourl,type);
            return Result.success();
        } catch (Exception e) {
            return Result.error("上传失败");
        }
    }
    @GetMapping("/videos/types/{typename}")
    public Result getTypeData(@PathVariable String typename){
        List<Videos> videos = videosService.getTypeVideos(typename);
        return Result.success(videos);
    }
    /**
     * 更新视频
     */
    @PutMapping("/videos/update")
    public Result updateVideo(Integer id,String title, String description, MultipartFile coverimage,String coverimageurl) throws IOException{
        log.info("coverimageurl为"+coverimageurl);
        try {
            AliYunOssUtils.deleteFileByUrl(coverimageurl);
            String headimage = AliYunOssUtils.uploadUser(coverimage);
            videosService.updateVideo(id,title,description,headimage);
            return Result.success();
        } catch (Exception e) {
           e.printStackTrace();
           return Result.error("操作失败");
        }
    }
    @GetMapping("/videos/users")
    public Result getSearchUser(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "20") Integer pageSize,
                                String keyword){
        PageBean pageBean= usersService.page(page,pageSize,keyword);
        return Result.success(pageBean);
    }
    @PutMapping("/videos/watchcount/{id}")
    public Result updateWatchcount(@PathVariable Integer id){
        videosService.updateWatchcount(id);
        return Result.success();
    }
    @GetMapping("/videos/watch/id/get")
    public Result getWatchAuthorId(@RequestParam String author){
        Integer id=usersService.getIdByUsername(author);
       return Result.success(id);
    }
}
