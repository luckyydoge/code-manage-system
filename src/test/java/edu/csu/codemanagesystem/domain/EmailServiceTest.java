package edu.csu.codemanagesystem.domain;

import com.aliyun.dm20151123.Client;
import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.service.IEmailService;
import edu.csu.codemanagesystem.domain.teacher.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@SpringBootTest
@Slf4j
public class EmailServiceTest {
    @Autowired
    EmailService emailService;


    @Test
    public void sendEmail() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 3);

        JobEntity jobEntity = JobEntity.builder()
                .title("中期汇报")
                .endTime(calendar.getTime()).build();
        String courseName = "应用基础实践二（Web技术+数据库+移动应用开发）";
        StudentEntity studentEntity1 = StudentEntity.builder()
                .email("2647461091@qq.com")
                .name("钟俊威")
                .build();
        StudentEntity studentEntity2 = StudentEntity.builder()
                .email("1750404601@qq.com")
                .name("张俊琛")
                .build();
        StudentEntity studentEntity3 = StudentEntity.builder()
                .email("2171513604@qq.com")
                .name("马宏琨")
                .build();
        StudentEntity studentEntity4 = StudentEntity.builder()
                .email("3502023457@qq.com")
                .name("胡展鸣")
                .build();
        StudentEntity studentEntity5 = StudentEntity.builder()
                .email("2402898955@qq.com")
                .name("王豪毅")
                .build();
        List<StudentEntity> studentEntityList = new ArrayList<>();
        studentEntityList.add(studentEntity1);
        studentEntityList.add(studentEntity2);
        studentEntityList.add(studentEntity3);
        studentEntityList.add(studentEntity4);
        studentEntityList.add(studentEntity5);
        emailService.sendEmail(studentEntityList, jobEntity, courseName);
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInit() throws Exception {
        Client client = emailService.init();
    }
}
