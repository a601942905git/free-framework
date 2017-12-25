package com.free.framework.util.pom;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * com.free.framework.util.pom.PomExtractorUtilsUtils
 * 根据jar生成pom文件信息
 * @author lipeng
 * @dateTime 2017/12/25 16:58
 */
public class PomExtractorUtils {

    private static StringBuilder stringBuilder = new StringBuilder(10240);
    private static String groupId;
    private static String artifactId;
    private static String version;


    /**
     * 根据jar所在的文件路径生成对应的pom信息
     * @param jarFilePath jar所在的文件路径 如D://jar
     */
    public static void generatePomInfo(String jarFilePath) {
        if (StringUtils.isEmpty(jarFilePath)) {
            throw new IllegalArgumentException("jarFilePath can not empty");
        }
        File cwd = new File(jarFilePath);
        File[] archives = cwd.listFiles(new JarFilter());
        for (int j = 0; j < archives.length; j++) {
            PomExtractorUtils.commence();
            PomExtractorUtils.analyzeJarFile(archives[j]);
        }
    }

    private static void commence() {
        stringBuilder.setLength(0);
    }

    private static void resetData() {
        groupId = null;
        artifactId = null;
        version = null;
    }

    private static void analyzeJarFile(File file) {
        if (!file.exists()) {
            return;
        }
        try (ZipFile zipFile = new ZipFile(file)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().endsWith("pom.xml")) {
                    resetData();
                    boolean hasParent = false;
                    boolean enterParent = false;
                    try (Scanner scanner = new Scanner(zipFile.getInputStream(entry), "US-ASCII")) {
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.contains("parent")) {
                                enterParent = !enterParent;
                                hasParent = true;
                            } else if (line.contains("<groupId>")) {
                                groupId = line;
                            } else if (!enterParent && line.contains("<artifactId>")) {
                                artifactId = line;
                            } else if (line.contains("<version>")) {
                                version = line;
                            } else if (!hasParent && groupId != null && artifactId != null && version != null) {
                                break;
                            } else if (line.contains("<dependencies>") || line.contains("<dependency>") || line.contains("<properties>") || line.contains("<profiles>") || line.contains("<plugins>")) {
                                break;
                            }
                        }
                        if (groupId != null && artifactId != null && version != null) {
                            System.out.println("<dependency>");
                            System.out.println(groupId);
                            System.out.println(artifactId);
                            System.out.println(version);
                            System.out.println("</dependency>");
                        } else {
                            stringBuilder.append("pom.xml解析异常，当前jar文件是" + file.getCanonicalPath() + ",解析失败的文件是" + entry.getName());
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return;
    }


    static class JarFilter implements FileFilter {
        @Override
        public boolean accept(File pathName) {
            String upcase = pathName.getName().toUpperCase();
            return upcase.endsWith(".JAR");
        }
    }
}
