package edu.csu.codemanagesystem.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils {

    /**
     * 将 InputStream 保存为文件
     *
     * @param inputStream 输入流
     * @param filePath    文件保存路径
     * @throws Exception 如果保存过程中发生错误
     */
    public static void saveInputStreamToFile(InputStream inputStream, String filePath) throws Exception {
        Path path = Paths.get(filePath);
        // 创建目标目录（如果它不存在）
        Path parentDir = path.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        try (OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
