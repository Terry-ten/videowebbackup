package com.zr.service.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.anno.MyLogs;
import com.zr.mapper.*;
import com.zr.pojo.*;
import com.zr.service.*;
import com.zr.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 曾瑞
 * @description 针对表【users】的数据库操作Service实现
 * @createDate 2023-04-03 12:36:44
 */
@Slf4j
@Service
public class UsersServiceImpl implements UsersService{
   public static final int ERROR=2;
   public static final int DUPLICATE=1;
   public static final int SUCCESS=0;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private VideosService videosService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private RepliesService repliesService;

    @Autowired
    private ReportsService reportsService;

    @Autowired

    private UserRoleService userRoleService;

    @Autowired
    private UserRoleMapper userRoleMapper;



    @Override
    public PageBean page(Integer page,Integer pagesize,String username, LocalDate begin, LocalDate end){
        PageHelper.startPage(page,pagesize);


        log.info("即将进行业务操作，调用selecetAllUsers方法");
        List<Users> users=usersMapper.selectAllUsers(username, begin, end);
        log.info("service层调用mapper方法成功，即将进行分页");
        Page<Users> page1 = (Page<Users>) users;

        return new PageBean(page1.getTotal(),page1.getResult());
    }

    @MyLogs
    @Override
    public int uploadUser(String username, String headimage, String password, String idcard, String phonenumber, String introduction) {
        LocalDateTime createtime=LocalDateTime.now();
        Users user=new Users();
        user.setCreatetime(createtime);
        user.setUpdatetime(createtime);
        user.setIdcard(idcard);
        user.setPhonenumber(phonenumber);
        user.setUsername(username);
        user.setPassword(MD5Util.md5(password));
        user.setHeadimage(headimage);
        if (introduction!=null){
            user.setIntroduction(introduction);
        }
        if(usersMapper.selectByUsername(username)!=0){
            return DUPLICATE;
        }else {
            try{usersMapper.insertUsers(user);
                userRoleMapper.insertUserRole(usersMapper.selectIdByname(user.getUsername()),user.getUsername());
            }catch (Exception e){
                e.printStackTrace();
                return ERROR;
            }
        }
        return SUCCESS;
    }

    @MyLogs
    @Transactional
    @Override
    public void deleteByadmin(String username) {
        try {
            usersMapper.deleteByadmin(username);
            videosService.deleteVideoByAuthor(username);
            commentsService.deleteCommentsBySpeaker(username);
            repliesService.deleteReplyByRepliar(username);
            reportsService.deleteReportsByReporter(usersMapper.selectAllByUsername(username).getId());
            userRoleService.deleteUserRoleByUsername(username);
        }catch (Exception e){
            e.printStackTrace();

        }

    }

    @MyLogs
    @Override
    public int updateUsers(Integer id, String username, String headimage, String password, String phonenumber, String introduction) {
        String oldname=usersMapper.selectUsernameById(id);
        LocalDateTime updatetime=LocalDateTime.now();
        Users user=new Users();
        user.setId(id);
        user.setUsername(username);
        user.setHeadimage(headimage);
        user.setPassword(MD5Util.md5(password));
        user.setPhonenumber(phonenumber);
        user.setIntroduction(introduction);
        user.setUpdatetime(updatetime);
        try {
            if(!(username.equals(oldname))){
                if(usersMapper.selectByUsername(username)>=1){
                    return DUPLICATE;
                }
            }
            usersMapper.updateUsers(user);
            videosService.updateVideoAuthor(username,oldname);
            commentsService.updateCommentSpeaker(username,oldname);
            repliesService.updateReplyRepliar(username,oldname);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    @Override
    public Users userLogin(Users user) {
        user.setPassword(MD5Util.md5(user.getPassword()));
        Users u = usersMapper.selectUsernameAndPassword(user);
        return u;
    }


    @Override
    public int addUser(Users user) {
        LocalDateTime createtime=LocalDateTime.now();
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setCreatetime(createtime);
        user.setUpdatetime(createtime);
        if(usersMapper.selectByUsername(user.getUsername())!=0){
            return DUPLICATE;
        }else {
            try{usersMapper.insertUsers(user);
                userRoleMapper.insertUserRole(usersMapper.selectIdByname(user.getUsername()),user.getUsername());
            }catch (Exception e){
                e.printStackTrace();
                return ERROR;
            }
        }
        return SUCCESS;
    }

    @Override
    public void updateUserPassword(Users users) {
        users.setPassword(MD5Util.md5(users.getPassword()));
        usersMapper.updateUserPassword(users);
    }

    @Override
    public void updateUsersHeadimage(String imageurl) {
        usersMapper.updateUserimage(imageurl);
    }

    @MyLogs
    @Transactional
    @Override
    public int updateUser(Integer id, String username, String phonenumber, String introduction, String headimage) {
        String oldname=usersMapper.selectUsernameById(id);
        LocalDateTime updatetime=LocalDateTime.now();
        Users user=new Users();
        user.setId(id);
        user.setUsername(username);
        user.setPhonenumber(phonenumber);
        user.setIntroduction(introduction);
        user.setHeadimage(headimage);
        user.setUpdatetime(updatetime);
        try {
           if(!(username.equals(oldname))){
               if(usersMapper.selectByUsername(username)>=1){
                   return DUPLICATE;
               }
           }
            usersMapper.updateUsers(user);
            videosService.updateVideoAuthor(username,oldname);
            commentsService.updateCommentSpeaker(username,oldname);
            repliesService.updateReplyRepliar(username,oldname);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    @Override
    public Users getOneUserById(Integer id) {
        return usersMapper.selectByIdUsers(id);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String username) {
        PageHelper.startPage(page,pageSize);
        List<Users> users=usersMapper.selectAllVideoUsers(username);
        Page<Users> page1= (Page<Users>) users;
        return new PageBean(page1.getTotal(),page1.getResult());
    }

    @Override
    public Integer getIdByUsername(String author) {
        Integer id = usersMapper.selectIdByname(author);
        return id;
    }

    @Override
    public String getQuestionByusername(String username) {
        return usersMapper.selectQuestionByUsername(username);
    }

    @Override
    public Users getOneUser(String username) {
        return usersMapper.selectAllByUsername(username);
    }


}
