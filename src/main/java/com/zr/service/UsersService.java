package com.zr.service;

import com.zr.pojo.PageBean;
import com.zr.pojo.Users;

import java.time.LocalDate;

/**
 * @author 曾瑞
 * @description 针对表【users】的数据库操作Service
 * @createDate 2023-04-03 12:36:44
 */
public interface UsersService  {

//    public List<Users> selectAllUsers();


    PageBean page(Integer page, Integer pagesize,String username, LocalDate begin, LocalDate end);

    int uploadUser(String username, String headimage, String password, String idcard, String phonenumber, String introduction);

    void deleteByadmin(String username);

    int updateUsers(Integer id, String username, String headimage, String password, String phonenumber, String introduction);

    Users userLogin(Users user);

    int addUser(Users user);

    void updateUserPassword(String password);

    void updateUsersHeadimage(String imageurl);

    int updateUser(Integer id, String username, String phonenumber, String introduction, String headimage);

    Users getOneUser(Integer id);

    PageBean page(Integer page, Integer pageSize, String username);

    Integer getIdByUsername(String author);
}