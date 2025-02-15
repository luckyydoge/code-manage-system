package edu.csu.codemanagesystem;

import edu.csu.codemanagesystem.infrastructure.dao.IUserDao;
import edu.csu.codemanagesystem.infrastructure.po.User;
import edu.csu.codemanagesystem.infrastructure.repository.ImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class ImportRepositoryTest {

    @Autowired
    ImportRepository importRepository;

    @Autowired
    IUserDao userDao;

    @Test
    public void testTeacherCount() {
        int count = importRepository.queryTeacherCount();
        log.info("teacher count {}", count);
    }

    @Test
    public void testInsertUserBatch() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUserId((long) 1);
        user1.setPassword("123456");
        user1.setType("teacher");
        User user2 = new User();
        user2.setUserId((long) 1);
        user2.setPassword("123456");
        user2.setType("teacher");
        userList.add(user1);
        userList.add(user2);
        userDao.insertUserBatch(userList);
    }

    @Test
    public void testQueryStudentCount() {
        int count = importRepository.queryStudentCount();
        log.info("student count {}", count);
    }

}
