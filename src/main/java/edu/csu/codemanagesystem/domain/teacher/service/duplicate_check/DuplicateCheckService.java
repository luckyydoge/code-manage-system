package edu.csu.codemanagesystem.domain.teacher.service.duplicate_check;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.teacher.model.entity.JobEntity;
import edu.csu.codemanagesystem.domain.teacher.model.valobj.DuplicateCheckReq;
import edu.csu.codemanagesystem.domain.teacher.model.valobj.DuplicateCheckRes;
import edu.csu.codemanagesystem.domain.teacher.model.valobj.FuckFormatVO;
import edu.csu.codemanagesystem.domain.teacher.repository.ITeacherRepository;
import edu.csu.codemanagesystem.domain.teacher.service.IDuplicateCheckService;
import edu.csu.codemanagesystem.domain.teacher.service.IEmailService;
import edu.csu.codemanagesystem.type.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DuplicateCheckService implements IDuplicateCheckService {

    private final String uploadPath = System.getProperty("user.dir")  + "/uploads";
    private final String url = "http://localhost:8088/checkRepeatRate";

    private final RestTemplate restTemplate;

    private final ITeacherRepository teacherRepository;
    private final IEmailService emailService;

    public DuplicateCheckService(ITeacherRepository teacherRepository, RestTemplate restTemplate, IEmailService emailService) {
        this.teacherRepository = teacherRepository;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
    }

    @Override
    public void duplicateCheck() {
        List<JobEntity> jobEntityList = teacherRepository.queryJobByFactor(JobEntity.builder().status("created").build());
        Date now = new Date();
        jobEntityList.forEach(jobEntity -> {
            if (now.after(jobEntity.getEndTime())) {
                String jobId = jobEntity.getJobId().toString();
                String jobDir = uploadPath + "/" + jobId;
                List<Path> pathList = new ArrayList<>();

                try {
                     pathList = Files.walk(Path.of(jobDir))
                            .filter(Files::isRegularFile)
                            .map(Path::toAbsolutePath)
                            .collect(Collectors.toList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<DuplicateCheckReq> duplicateCheckReqList = new ArrayList<>();
                pathList.forEach(path -> {
                    DuplicateCheckReq duplicateCheckReq = DuplicateCheckReq.builder()
                            .file_path(path.toString())
                            .name(path.getFileName().toString())
                            .build();
                    duplicateCheckReqList.add(duplicateCheckReq);
                });
                log.info("{}", duplicateCheckReqList);
                FuckFormatVO fuckFormatVO = FuckFormatVO.builder()
                        .threshold(0.7)
                        .objects(duplicateCheckReqList)
                        .build();
                ObjectMapper objectMapper = new ObjectMapper();
                String s = "";
                try {
                    s = objectMapper.writeValueAsString(fuckFormatVO);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                log.info("req : {} ", s);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity<>(s, httpHeaders);
                ResponseEntity<Response<List<DuplicateCheckRes>>> response = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<Response<List<DuplicateCheckRes>>>() {});
                log.info("response: {}", response);
                log.info("request : {}", request);
                Response<List<DuplicateCheckRes>> data = response.getBody();
                log.info("response json: {}", data);
                List<DuplicateCheckRes> duplicateCheckResList = data.getData();
                log.info("duplicate list: {}", duplicateCheckResList);
                TeacherEntity teacherEntity = teacherRepository.queryTeacherByClassId(jobEntity.getClassId());
                String email = teacherEntity.getEmail();
                emailService.sendDuplicateCheckEmail(duplicateCheckResList, email);
                jobEntity.setStatus("stopped");
                teacherRepository.updateJobById(jobEntity);
            }
        });

    }
}
