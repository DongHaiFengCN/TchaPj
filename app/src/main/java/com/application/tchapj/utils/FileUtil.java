package com.application.tchapj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Create by zyy on 2019/4/23
 * Description:
 */
public class FileUtil {

    public static void copyFile(File src, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);

            byte[] by = new byte[1024];//准备车子
            int len = 0; //假设车上没有人

            try {
                while (-1 != (len = is.read(by))) { //开始慢慢下车和上车
                    os.write(by, 0, len); //下车
                }
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                os.flush();
                is.close();
                os.close();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件未找到");
        }
    }
}
