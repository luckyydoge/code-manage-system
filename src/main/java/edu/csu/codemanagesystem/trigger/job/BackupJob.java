package edu.csu.codemanagesystem.trigger.job;

import edu.csu.codemanagesystem.domain.backup.service.IBackup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class BackupJob {

    @Autowired
    IBackup backupService;

    @Scheduled(cron = "0 0 2 * * *")
    public void backup() {
        backupService.backup();
        log.info("backup the database");

    }
}
