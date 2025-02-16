package edu.csu.codemanagesystem.domain.admin.service;

public interface IBackupService {
    public void backup();

    public Boolean setInterval(long interval);
}
