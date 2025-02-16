package edu.csu.codemanagesystem.domain;

import edu.csu.codemanagesystem.domain.admin.service.ITeacherService;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherServiceTest {

    @Autowired
    ITeacherService teacherService;

    @Test
    public void testCreateTeacher(){
        TeacherEntity teacherEntity = TeacherEntity.builder()
                .name("test")
                .email("test@qq.com")
                .build();
        teacherService.addTeacher(teacherEntity);
    }
}
