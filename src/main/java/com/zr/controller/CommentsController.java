package com.zr.controller;



import com.zr.pojo.PageBean;
import com.zr.pojo.Replies;
import com.zr.service.CommentsService;
import com.zr.service.PermissionService;
import com.zr.service.RepliesService;
import com.zr.service.RolePermissionService;
import com.zr.utils.PermissionUtil;
import com.zr.utils.Result;
import com.zr.pojo.Comments;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    @Autowired
    private RepliesService repliesService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionService permissionService;
    @PostMapping("/comments/insert")
    public Result InSertComments(@RequestBody Comments comments){
        commentsService.inSertComments(comments);
            return Result.success();
    }
    @GetMapping("/comments")
    public Result getAllComments(
            HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String speaker, Integer videoid,
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
        PageBean pageBean= commentsService.page(page,pageSize,speaker,videoid,begin,end);
        return Result.success(pageBean);
    }
    @DeleteMapping("/comments/replies/delete/{id}")
    public Result deleteReplyById(@PathVariable Integer id){
        try{
            repliesService.deleteReplyById(id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("删除回复失败");
        }

        return Result.success();
    }
    @DeleteMapping("/comments/delete/{id}")
    public Result deleteCommentsById(@PathVariable Integer id){
        try{
            commentsService.deleteCommentsById(id);
        }catch (Exception e){
            return Result.error("删除评论失败");
        }
        return Result.success();
    }
    @GetMapping("/comments/replies")
    public Result getRepliesById(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 Integer commentId){
        PageBean pageBean =repliesService.page(page,pageSize,commentId);

        return Result.success(pageBean);
    }
    @GetMapping("/comments/{targetid}")
    public Result getCommentByPathId(@PathVariable Integer targetid){
        Comments comments=commentsService.getCommentById(targetid);

        return Result.success(comments);
    }

    @GetMapping("/comments/videos/{id}")
    public Result getVideoComments(@PathVariable Integer id){
        List<Comments> comments=commentsService.getVideoComments(id);
        return Result.success(comments);
    }
    @PostMapping("/comments/add")
    public Result addMyComments(@RequestBody Comments comment){
        try {
            commentsService.inSertComments(comment);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("评论失败！");
        }
    }
    @GetMapping("/comments/replies/{commentid}")
    public Result getRepliesById(@PathVariable Integer commentid){

        List<Replies> replies = repliesService.selectByCommentId(commentid);
        return Result.success(replies);
    }
    @PostMapping("/comments/replies/add")
    public Result addCommentReply(@RequestBody Replies reply){
        try {
            repliesService.addCommentReply(reply);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("操作失败");
        }
    }
}
