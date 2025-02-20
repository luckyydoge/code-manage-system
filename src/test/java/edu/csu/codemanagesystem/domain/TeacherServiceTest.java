package edu.csu.codemanagesystem.domain;

import edu.csu.codemanagesystem.domain.admin.service.ITeacherManageService;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.teacher.service.IDuplicateCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class TeacherServiceTest {

    @Autowired
    ITeacherManageService teacherService;

    @Autowired
    IDuplicateCheckService duplicateCheckService;

    @Test
    public void testCreateTeacher(){
        TeacherEntity teacherEntity = TeacherEntity.builder()
                .name("test")
                .email("test@qq.com")
                .build();
        teacherService.addTeacher(teacherEntity);
    }

    @Test
    public void test() {
        String jobDir = System.getProperty("user.dir") + "/uploads/2";
        try {
            List<Path> pathList = Files.walk(Path.of(jobDir))
                    .filter(Files::isRegularFile)
                    .map(Path::toAbsolutePath)
                    .collect(Collectors.toList());
            pathList.forEach(path -> {
                System.out.println("absolute path: " +  path);
                System.out.println("name : " + path.getFileName().toString());
                System.out.println("parent name : " + path.getParent().getFileName().toString());

            });
            System.out.println(pathList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDuplicateCheck(){
        duplicateCheckService.duplicateCheck();
    }
}
