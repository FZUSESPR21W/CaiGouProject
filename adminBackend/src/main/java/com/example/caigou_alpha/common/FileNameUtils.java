package com.example.caigou_alpha.common;


import java.util.UUID;

/**
 * @Auther: wyx
 * @Date: 2019-04-08 10:08
 * @Description:
 */

public class FileNameUtils {

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return UUID.randomUUID() + FileNameUtils.getSuffix(fileOriginName);

    }


}

