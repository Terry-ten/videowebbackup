package com.zr.controller;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author zr
 * @date 2023/4/11 12:40
 */
public class UseLessController {
    /*@PostMapping("/upload/insert")
    public Results UploadUser(String username, MultipartFile headimage,String password,
                              String idcard,String phonenumber,String introduction) throws IOException {

        String newname= getnewname(headimage);
        String headimagenow="http://localhost:8080/upload/"+newname;
        headimage.transferTo(new File("E:/images/headimages/"+newname));
        if(usersService.insertUsers(username, headimagenow, password, idcard,phonenumber,introduction)==UsersServiceImpl.DUPLICATE){
            return Results.Resultserror("用户名重复");
        }else if(usersService.insertUsers(username, headimagenow, password, idcard,phonenumber,introduction)==UsersServiceImpl.ERROR){
            return Results.Resultserror("新增用户失败");
        }
        return Results.Resultssuccess();
    }
    @PostMapping("/upload/update")
    public Results EditUser(String username, MultipartFile headimage,String password,
                            String idcard,String phonenumber,String introduction) throws IOException {

        String newname= getnewname(headimage);
        String headimagenow="http://localhost:8080/upload/"+newname;
        headimage.transferTo(new File("E:/images/headimages/"+newname));
        if(usersService.updateUsers(username, headimagenow, password,idcard,phonenumber,introduction)==UsersServiceImpl.DUPLICATE){
            return Results.Resultserror("用户名重复");
        }else if(usersService.updateUsers(username, headimagenow, password,idcard,phonenumber,introduction)==UsersServiceImpl.ERROR){
            return Results.Resultserror("修改用户失败");
        }
        return Results.Resultssuccess();
    }
    public String getnewname(MultipartFile headimage){
        return UUID.randomUUID()+headimage.getOriginalFilename().substring(headimage.getOriginalFilename().lastIndexOf("."));
    }*/
}
