package edu.csu.codemanagesystem.domain.teacher.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DuplicateCheckRes {
    private String rate;
    private String student1;
    private String student2;
}
