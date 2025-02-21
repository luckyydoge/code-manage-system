package edu.csu.codemanagesystem.domain.admin.service.backup;

import edu.csu.codemanagesystem.domain.admin.service.IBackupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BackupServiceService implements IBackupService {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${db-port}")
    private String port;

    private String backupFilePath = "./docs/sql/backup.sql";

    private String cmd = "docker.exe";

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private long interval = 24 * 60 * 60 * 1000;

    @Override
    public void backup() {

        try {
            Path filepath = Paths.get(backupFilePath);
            if (!Files.exists(filepath)) {
                Path parent = filepath.getParent();
                if (!Files.exists(parent)) {
                        Files.createDirectories(parent);
                }
                Files.createFile(filepath);
            }
//            Path parent = filepath.getParent();
//            Files.createDirectories(parent);
//            Files.createFile(filepath);
            ProcessBuilder pb = new ProcessBuilder("mysqldump", "-u", username, "-P", port, "-p" + password, "code_manage_system");
            pb.redirectOutput(new File(backupFilePath));
            List<String> commands = pb.command();
            commands.forEach(command -> {
                System.out.print(command);
                System.out.print(" ");
            });
            long startTime = System.currentTimeMillis();
            Process p = pb.start();
            int exitCode = p.waitFor();
            long endTime = System.currentTimeMillis();
            log.info("use time : {}ms", (endTime - startTime));
            if (exitCode != 0) {
                log.info("failed");
            }
            log.info("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean setInterval(long interval) {
        this.interval = interval;
        scheduler.shutdown();
        scheduler = Executors.newScheduledThreadPool(1);
        startTask();
        return true;
    }

    @Override
    public Long queryInterval() {
        return interval;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void startTask() {
        scheduler.scheduleAtFixedRate(this::backup, 0, interval, TimeUnit.MILLISECONDS);
    }
}
