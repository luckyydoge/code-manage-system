package edu.csu.codemanagesystem.domain.teacher.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DuplicateCheckReq {
    private String file_path;
    private String name;
}
