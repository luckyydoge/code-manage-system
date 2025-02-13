package edu.csu.codemanagesystem.trigger.http.controller;

import edu.csu.codemanagesystem.domain.backup.service.IBackup;
import edu.csu.codemanagesystem.type.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class BackupController {

    @Autowired
    IBackup backupService;

    @GetMapping("/admin/backupDatabase")
    public Response<Boolean> backupDatabase() {
        backupService.backup();
        return Response.<Boolean>builder()
                .code("000")
                .info("")
                .build();
    }

    @GetMapping("/admin/downloadBackupFile")
    public void downloadBackupFile(HttpServletResponse response) {
        Path filepath = Paths.get("./docs/sql/backup.sql");
        try {
            Resource resource = new UrlResource(filepath.toUri());
            if (resource.exists() && resource.isReadable()) {
                try (InputStream inputStream = resource.getInputStream()) {
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
