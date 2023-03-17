package com.photograph.system.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: shijinyong
 * @Date: 2023/2/27 11:34
 * @Description: 图片上传工具类
 */
public class UploadUtils {

    //定义图片上传的位置
    private static final String BASE_PATH = "E:\\apache-tomcat-9.0.56\\webapps\\upload\\";

    //定义访问图片的路径
    private static final String SERVER_PATH = "http://localhost:8080/upload/";

    //定义上传方法
    public static String upload(MultipartFile file){
        //获得上传文件的名称
        String filename = file.getOriginalFilename();
        //保证图片在服务器的唯一性
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //将生成的uuid和filename进行拼接
        String newFileName = uuid+"-"+filename;
        //创建一个文件实例对象
        File image = new File(BASE_PATH, newFileName);
        //如果文件不存在，则进行上传操作
        if(!image.exists()){
            //对文件进行上传操作
            try {
                file.transferTo(image);
            } catch (IOException e) {
                return null;
            }
        }
        return SERVER_PATH+newFileName;
    }

}
