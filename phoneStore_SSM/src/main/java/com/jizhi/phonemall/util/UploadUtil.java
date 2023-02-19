package com.jizhi.phonemall.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * 上传的工具类
 */
public class UploadUtil {
    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    public static String fileUpload(MultipartFile file) {
        //1.判断是否有上传文件
        //Objects.isNull() 判断是否为null
        //file.isEmpty()    判断文件是否为空
        //file.getOriginalFilename()上传文件的文件名
        if (Objects.isNull(file) || file.isEmpty() || Objects.isNull(file.getOriginalFilename())) {
            return null;
        }
        //2.有文件且符合要求
        String fileName = file.getOriginalFilename();//获取上传文件的文件名称
        //绝对路径
        //相对路径

        //获取以/pic结尾的绝对路径
        String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/pic");

        //对文件名进行处理，防止重复
        //时间戳方式
        String currentTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        //修改文件名
        //fileName.substring(fileName.lastIndexOf("."), fileName.length())获取文件后缀
        fileName = currentTime + (new Random().nextInt(899) + 100) + fileName.substring(fileName.lastIndexOf("."), fileName.length());

        File saveFile = new File(path, fileName);
        //判断文件是否存在
        if (!saveFile.exists()) {
            //创建文件
            saveFile.mkdirs();
        }
        try {
            //将内存中地文件信息写入磁盘中
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return "\\phoneStore_SSM\\pic\\" + fileName;
    }
}
