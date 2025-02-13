package edu.csu.codemanagesystem.domain.backup.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class BackupService implements IBackup {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private final String port = "13306";

    private String backupFilePath = "./docs/sql/backup.sql";

    private String cmd = "mysqldump";

    @Override
    public void backup() {

        try {
            ProcessBuilder pb = new ProcessBuilder(cmd, "-u", username, "-P", port, "-p" + password, "code_manage_system");
            pb.redirectOutput(new File(backupFilePath));
            List<String> commands = pb.command();
            commands.forEach(command -> {
                System.out.print(command);
                System.out.print(" ");
            });
            Process p = pb.start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                log.info("failed");
            }
            log.info("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String command = String.format("mysqldump -P %s -u %s -p%s code_manage_system > %s",
//                port, username, password, backupFilePath);
//        log.info(command);

//        try {
//            Process process = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", command});
//            int status = process.waitFor();
//            if (0 != status) {
//                log.error("mysqldump command failed");
//            } else {
//                log.info("mysqldump command success");
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            Process process = Runtime.getRuntime().exec(command);
//            InputStream inputStream = process.getErrorStream();
//            int byteValue;
//            while ((byteValue = inputStream.read()) != -1) {
//                System.out.print((char) byteValue);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
