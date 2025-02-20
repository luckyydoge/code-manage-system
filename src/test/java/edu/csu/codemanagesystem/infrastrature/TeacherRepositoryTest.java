package edu.csu.codemanagesystem.infrastrature;

import edu.csu.codemanagesystem.domain.teacher.repository.ITeacherRepository;
import edu.csu.codemanagesystem.infrastructure.dao.IStudentClassDao;
import edu.csu.codemanagesystem.infrastructure.dao.IStudentDao;
import edu.csu.codemanagesystem.infrastructure.dao.IStudentJobDao;
import edu.csu.codemanagesystem.infrastructure.po.Student;
import edu.csu.codemanagesystem.infrastructure.po.StudentJob;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TeacherRepositoryTest {
    @Autowired
    ITeacherRepository iTeacherRepository;

    @Autowired
    IStudentClassDao iStudentClassDao;

    @Autowired
    IStudentDao iStudentDao;

    @Autowired
    IStudentJobDao iStudentJobDao;

    @Test
    public void studentClassDaoTest() {
        List<Long> list = iStudentClassDao.queryStudentIdByClassId((long)400001);
        System.out.println(list);
    }

    @Test
    public void studentDaoTest() {
        List<Long> studentIdList = new ArrayList<>();
        studentIdList.add((long)300001);
        studentIdList.add((long)300002);
        studentIdList.add((long)300003);
        List<Student> studentList = iStudentDao.queryStudentByStudentIdList(studentIdList);
        System.out.println(studentList);
    }

//    @Test
//    public void studentJobDaoTest() {
//        List<StudentJob> studentJobList = new ArrayList<>();
//        StudentJob studentJob1 = new StudentJob();
//        studentJob1.setStudentId((long)300001);
//        studentJob1.setJobId((long)1);
//        StudentJob studentJob2 = new StudentJob();
//        studentJob2.setStudentId((long)300002);
//        studentJob2.setJobId((long)1);
//        StudentJob studentJob3 = new StudentJob();
//        studentJob3.setStudentId((long)300003);
//        studentJob3.setJobId((long)1);
//        studentJobList.add(studentJob1);
//        studentJobList.add(studentJob2);
//        studentJobList.add(studentJob3);
//        iStudentJobDao.insertStudentJobBatch(studentJobList);
//    }

    @Test
    public void queryCourseNameByCourseIdTest() {
        String name = iTeacherRepository.queryCourseNameByCourseId((long)1001);
        System.out.println(name);
    }
}
