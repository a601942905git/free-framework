package com.free.framework.io;


import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * com.free.framework.io.PathTest
 *
 * @author lipeng
 * @dateTime 2018/9/10 上午10:11
 */
public class PathTest {

    public static void main(String[] args) {

    }

    /**
     * 获取文件对应的path
     */
    @Test
    public void buildPath() {
        Path path = Paths.get("/Users/pengli/Downloads/1535621212836.jpg");
        System.out.println("文件名称：" + path.getFileName());
        System.out.println("文件节点数：" + path.getNameCount());
        System.out.println("文件上级目录：" + path.getParent());
        System.out.println("文件根目录：" + path.getRoot());
    }

    /**
     * 创建目录、文件
     * @throws IOException
     */
    @Test
    public void create() throws IOException {
        // 创建目录
        Path path = Paths.get("/Users/pengli/software/test");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // 创建文件
        Path path1 = Paths.get("/Users/pengli/software/test/test.txt");
        if (!Files.exists(path1)) {
            Files.createFile(path1);
        }
    }

    /**
     * 获取文件属性
     * @throws IOException
     */
    @Test
    public void getFileProperties() throws IOException {
        Path path = Paths.get("/Users/pengli/software/test/test.txt");
        System.out.println("文件的最后修改时间：" + Files.getLastModifiedTime(path));
        System.out.println("文件的拥有者：" + Files.getOwner(path));
        System.out.println("文件权限：" + Files.getPosixFilePermissions(path));
        System.out.println("文件大小：" + Files.size(path));
    }

    /**
     * 往文件中写数据
     * @throws IOException
     */
    @Test
    public void writeData2File() throws IOException {
        Path path = Paths.get("/Users/pengli/software/test/test.txt");
        BufferedWriter writer = Files.newBufferedWriter(path);
        String str = "write file test\n";
        writer.write(str);
        writer.flush();
        writer.close();
    }

    /**
     * 读取文件中的数据
     * @throws IOException
     */
    @Test
    public void readFile() throws IOException {
        Path path = Paths.get("/Users/pengli/software/test/test.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        StringBuilder content = new StringBuilder();
        String readContent = "";
        while ((readContent = reader.readLine()) != null) {
            content.append(readContent);
            content.append("\n");
        }
        System.out.println(content.toString());
    }

    /**
     * 通过字节的方式读取数据并写入新的文件中
     * @throws IOException
     */
    @Test
    public void getInputStream() throws IOException {
        Path path1 = Paths.get("/Users/pengli/software/test/test1.txt");
        if (Files.exists(path1)) {
            Files.createFile(path1);
        }

        Path path = Paths.get("/Users/pengli/software/test/test.txt");
        InputStream inputStream = Files.newInputStream(path);
        byte[] buffers = new byte[1024];

        OutputStream outputStream = Files.newOutputStream(path1);

        while (inputStream.read(buffers, 0, buffers.length) != -1) {
            outputStream.write(buffers, 0 , buffers.length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    /**
     * 遍历目录下面的文件
     * @throws IOException
     */
    @Test
    public void traverseDirectory() throws IOException {
        Path path = Paths.get("/Users/pengli/software");
        Stream<Path> pathStream = Files.list(path);
        pathStream.forEach((p) -> System.out.println(p.getFileName()));
    }

    /**
     * 复制文件，目标文件会自动创建
     * @throws IOException
     */
    @Test
    public void copyFile() throws IOException {
        Path sourcePath = Paths.get("/Users/pengli/software/test/test.txt");
        Path targetPath = Paths.get("/Users/pengli/software/test/test_copy.txt");
        Files.copy(sourcePath, targetPath);
    }
}
