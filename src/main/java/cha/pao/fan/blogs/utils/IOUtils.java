package cha.pao.fan.blogs.utils;

import java.io.File;

public class IOUtils {
    /**
     * 删除文件或者文件夹下所有文件
     * @param file
     */
    public static void deleteDir(File file){
        if(file.isDirectory()){
            for(File f:file.listFiles()){
                deleteDir(f);
            }
        }else{
            file.delete();
        }
    }
}
