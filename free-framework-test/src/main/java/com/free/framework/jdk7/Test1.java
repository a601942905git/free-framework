package com.free.framework.jdk7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * com.free.framework.jdk7.Test1
 * try-with-resource
 * @author lipeng
 * @dateTime 2017/8/13 11:25
 */
public class Test1 {
    public static void main(String[] args) {
        try (InputStream inputStream = new FileInputStream("D://a.txt")){
            int len = inputStream.read();
            StringBuilder sb = new StringBuilder();
            while (len > 0) {
                sb.append((char)len);
                len = inputStream.read();
            }
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
