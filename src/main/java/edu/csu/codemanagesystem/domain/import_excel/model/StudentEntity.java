package edu.csu.codemanagesystem.domain.import_excel.model;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.*;
import lombok.AllArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentEntity {
    private Long studentId;

    @ExcelProperty("学生名称")
    private String name;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("邮箱地址")
    private String email;

    @ExcelProperty("电话号码")
    private String phoneNumber;

    @ExcelProperty("行政班级")
    private String administrativeClass;
}
