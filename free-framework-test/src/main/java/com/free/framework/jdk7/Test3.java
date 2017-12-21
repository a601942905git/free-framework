package com.free.framework.jdk7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * com.free.framework.jdk7.Test3
 * 读写文件
 *
 * @author lipeng
 * @dateTime 2017/8/13 12:14
 */
public class Test3 {

    public static void main(String[] args) {
        readFileBytes();
        System.out.println("=====================");
        readFileLine();
        System.out.println("=====================");
        writeFile();
    }

    private static void readFileBytes() {
        Path path = Paths.get("D://a.txt");
        try {
            byte[] fileContentBytes = Files.readAllBytes(path);
            String fileContent = new String(fileContentBytes, StandardCharsets.UTF_8);
            System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFileLine() {
        Path path = Paths.get("D://a.txt");
        try {
            List<String> fileContentLines = Files.readAllLines(path);
            StringBuilder stringBuilder = new StringBuilder();
            fileContentLines.stream().forEach((fileLine) -> stringBuilder.append(fileLine + "\n"));
            System.out.println(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile() {
        Path path = Paths.get("D://b.txt");
        try {
            Files.write(path, "Hello Jdk7".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
