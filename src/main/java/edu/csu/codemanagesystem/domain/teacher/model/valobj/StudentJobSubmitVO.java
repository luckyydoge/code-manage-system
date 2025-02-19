package edu.csu.codemanagesystem.domain.teacher.model.valobj;

import edu.csu.codemanagesystem.domain.teacher.model.entity.StudentJobEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StudentJobSubmitVO {
    private Long jobId;
    private Long studentId;
    private MultipartFile file;

    public StudentJobEntity getStudentJobEntity() {
        return new StudentJobEntity(jobId, studentId, "submitted");
    }
}
