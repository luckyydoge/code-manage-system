package edu.csu.codemanagesystem.domain.teacher.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StudentJobSubmitTextVO {
    private String jobText;
    private Long studentId;
    private Long jobId;
}
