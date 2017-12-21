package com.free.framework.jdk7;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * com.free.framework.jdk7.Test2
 * 优雅捕获多个异常
 * @author lipeng
 * @dateTime 2017/8/13 12:08
 */
public class Test2 {
    public static void main(String[] args) {
        try {
            Thread.sleep(20000);
            FileInputStream fis = new FileInputStream("D:\\a.txt");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
