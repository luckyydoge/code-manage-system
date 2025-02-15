package edu.csu.codemanagesystem.domain.import_excel.model;

import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    private Long studentId;
    private String name;
    private String sex;
    private String email;
    private String phoneNumber;
    private String administrativeClass;
}
