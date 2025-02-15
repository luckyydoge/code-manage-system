package edu.csu.codemanagesystem.domain.import_excel.model;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TeacherEntity {
    private Long teacherId;

    @ExcelProperty("教师名称")
    private String name;

    @ExcelProperty("邮箱地址")
    private String email;
}
