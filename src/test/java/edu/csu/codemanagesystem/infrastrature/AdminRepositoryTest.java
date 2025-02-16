package edu.csu.codemanagesystem.infrastrature;

import edu.csu.codemanagesystem.domain.admin.model.entity.SemesterEntity;
import edu.csu.codemanagesystem.domain.admin.repository.IAdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AdminRepositoryTest {
    @Autowired
    IAdminRepository adminRepository;

    @Test
    public void testQueryCurrentSemester() {
        SemesterEntity semesterEntity = adminRepository.queryCurrentSemester();
        log.info(semesterEntity.toString());
    }

}
