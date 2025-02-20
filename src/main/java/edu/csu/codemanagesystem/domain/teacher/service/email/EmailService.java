package edu.csu.codemanagesystem.domain.teacher.service.email;

import com.aliyun.dm20151123.Client;
import com.aliyun.dm20151123.models.SingleSendMailRequest;
import com.aliyun.dm20151123.models.SingleSendMailResponse;
import com.aliyun.teaopenapi.models.Config;
import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.model.valobj.DuplicateCheckRes;
import edu.csu.codemanagesystem.domain.teacher.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class EmailService implements IEmailService {

    private final com.aliyun.dm20151123.Client dmClient = init();

    private final String textBodyFormat = "%s同学, 请及时完成%s课程的%s作业, 截止时间为%s";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public EmailService() throws Exception {
    }

    public com.aliyun.dm20151123.Client init() throws Exception {
        Config config = new Config();
        log.info("key id : {}", System.getenv("A_LI_YUN_EMAIL_ACCESS_KEY_ID"));
        log.info("key secret : {}", System.getenv("A_LI_YUN_EMAIL_ACCESS_KEY_SECRET"));
        config.setAccessKeySecret(System.getenv("A_LI_YUN_EMAIL_ACCESS_KEY_SECRET"));
        config.setAccessKeyId(System.getenv("A_LI_YUN_EMAIL_ACCESS_KEY_ID"));
        config.regionId = "cn-hangzhou";
        config.endpoint = "dm.aliyuncs.com";
        return new Client(config);
    }

    @Override
    public void sendEmail(List<StudentEntity> students, JobEntity job, String courseName) {
        String jobTitle = job.getTitle();
        Date endTime = job.getEndTime();
        Thread.startVirtualThread(() -> students.forEach(studentEntity -> {
            SingleSendMailRequest singleSendMailRequest = new SingleSendMailRequest()
                    .setAccountName("zjc@code.manage.system.rabbit-dog.xyz")
                    .setAddressType(1)
                    .setReplyToAddress(true)
                    .setSubject("新作业发布通知");
            singleSendMailRequest.setToAddress(studentEntity.getEmail());
            singleSendMailRequest.setTextBody(String.format(textBodyFormat, studentEntity.getName(), courseName, jobTitle, dateFormat.format(endTime)));
            log.info("1");
            try {
                SingleSendMailResponse response = dmClient.singleSendMail(singleSendMailRequest);
                log.info("send response : {} ", response.toString());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }));
    }

    @Override
    public void sendDuplicateCheckEmail(List<DuplicateCheckRes> duplicateCheckResList, String email) {
        log.info("send duplicate check email to {}", email);
        SingleSendMailRequest singleSendMailRequest = new SingleSendMailRequest()
                .setAccountName("zjc@code.manage.system.rabbit-dog.xyz")
                .setAddressType(1)
                .setReplyToAddress(true)
                .setSubject("查重结果");
        singleSendMailRequest.setToAddress(email);
        StringBuilder stringBuilder = new StringBuilder();
        duplicateCheckResList.forEach(duplicateCheckRes -> {
            stringBuilder.append(duplicateCheckRes.getStudent1()).append("  ").append(duplicateCheckRes.getStudent2())
                    .append("  ").append("查重率为:").append(duplicateCheckRes.getRate()).append("\n");
        });
        String textBody = stringBuilder.toString();
        singleSendMailRequest.setTextBody(textBody);
        log.info("test : {}", textBody);
        try {
            SingleSendMailResponse response = dmClient.singleSendMail(singleSendMailRequest);
            log.info("send response : {} ", response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
