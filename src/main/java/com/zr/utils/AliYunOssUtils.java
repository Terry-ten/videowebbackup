package com.zr.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Slf4j
public class AliYunOssUtils {

    public static String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
    public static String accessKeyId = "";
    public static String accessKeySecret = "";
    public static String bucketName = "zr-video-web";



    /**
     * 实现上传图片到OSS
     */
    public static String uploadUser(MultipartFile file) throws IOException {
//        endpoint=endpoint+"/headimages";
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        originalFilename="headimages/"+fileName;
        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, originalFilename, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] +"/"+ originalFilename;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }
    public static String uploadVideo(MultipartFile file) throws IOException {


        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        originalFilename="coverimages/"+fileName;
        if(fileName.endsWith("mp4")){
            originalFilename="videos/"+fileName;
        }
        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, originalFilename, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + originalFilename;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }
    public static String uploadDataBase(MultipartFile file) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();
        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        originalFilename="database/"+originalFilename;
        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, originalFilename, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + originalFilename;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

        /**
         * 根据文件 URL 删除阿里云 OSS 上的文件
         *
         * @param fileUrl 文件 URL
         */
        public static void deleteFileByUrl(String fileUrl) {
            // 创建 OSS 客户端
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 根据 URL 获取文件名（包括路径）
            String[] urls = fileUrl.split("/");
            log.info(Arrays.toString(urls));
            String fileName = urls[urls.length-2]+"/"+urls[urls.length-1];

            // 删除文件
            ossClient.deleteObject(bucketName, fileName);

            // 关闭 OSS 客户端
            ossClient.shutdown();
        }


}
