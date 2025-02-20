package edu.csu.codemanagesystem.trigger.job;

import edu.csu.codemanagesystem.domain.teacher.service.IDuplicateCheckService;
import edu.csu.codemanagesystem.domain.teacher.service.duplicate_check.DuplicateCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DuplicateCheckJob {

    @Autowired
    private IDuplicateCheckService duplicateCheckService;

    @Scheduled(cron = "0 0 * * * ?")
    public void duplicateCheck() {
        duplicateCheckService.duplicateCheck();
    }
}
