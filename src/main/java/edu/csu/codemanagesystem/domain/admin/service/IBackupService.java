package edu.csu.codemanagesystem.domain.admin.service;

public interface IBackupService {
    void backup();

    Boolean setInterval(long interval);

    Long queryInterval();
}
