package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.import_excel.repository.IImportRepository;
import edu.csu.codemanagesystem.infrastructure.dao.IStudentClassDao;
import edu.csu.codemanagesystem.infrastructure.dao.IStudentDao;
import edu.csu.codemanagesystem.infrastructure.dao.ITeacherDao;
import edu.csu.codemanagesystem.infrastructure.dao.IUserDao;
import edu.csu.codemanagesystem.infrastructure.po.Student;
import edu.csu.codemanagesystem.infrastructure.po.StudentClass;
import edu.csu.codemanagesystem.infrastructure.po.Teacher;
import edu.csu.codemanagesystem.infrastructure.po.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ImportRepository implements IImportRepository {

    @Autowired
    private IStudentClassDao studentClassDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ITeacherDao teacherDao;

    @Autowired
    private IStudentDao studentDao;

    @Override
    public int queryTeacherCount() {
        return teacherDao.queryTeacherCount();
    }

    @Override
    public void insertTeacherBatch(List<TeacherEntity> cacheList) {
        List<Teacher> teacherList = new ArrayList<>(cacheList.size());
        cacheList.forEach(teacherEntity -> {
            Teacher teacher = new Teacher();
            teacher.setTeacherId(teacherEntity.getTeacherId());
            teacher.setName(teacherEntity.getName());
            teacher.setEmail(teacherEntity.getEmail());
            teacherList.add(teacher);
        });
        teacherDao.insertTeacherBatch(teacherList);
    }

    @Override
    public void insertTeacherBatchIntoUsers(List<TeacherEntity> cacheList) {
        List<User> userList = new ArrayList<>(cacheList.size());
        cacheList.forEach(teacherEntity -> {
            User user = new User();
            user.setUserId(teacherEntity.getTeacherId());
            user.setType("teacher");
            userList.add(user);
        });

        userDao.insertUserBatch(userList);


    }

    @Override
    public int queryStudentCount() {
        return studentDao.queryStudentCount();
    }

    @Override
    public void insertStudentBatch(List<StudentEntity> cacheList) {
        List<Student> studentList = new ArrayList<>(cacheList.size());
        cacheList.forEach(studentEntity -> {
            Student student = new Student();
            student.setStudentId(studentEntity.getStudentId());
            student.setName(studentEntity.getName());
            student.setEmail(studentEntity.getEmail());
            student.setSex(studentEntity.getSex());
            student.setAdministrativeClass(studentEntity.getAdministrativeClass());
            student.setPhoneNumber(studentEntity.getPhoneNumber());
            studentList.add(student);
        });
        studentDao.insertStudentBatch(studentList);
    }

    @Override
    public void insertStudentBatchToUsers(List<StudentEntity> cacheList) {
        List<User> userList = new ArrayList<>(cacheList.size());
        cacheList.forEach(studentEntity -> {
            User user = new User();
            user.setUserId(studentEntity.getStudentId());
            user.setType("teacher");
            userList.add(user);
        });

        userDao.insertUserBatch(userList);

    }

    @Override
    public void insertStudentBatchToStudentClass(List<StudentEntity> cacheList, Long classId) {
        List<StudentClass> studentClassList = new ArrayList<>(cacheList.size());
        cacheList.forEach(studentEntity -> {
            StudentClass studentClass = new StudentClass();
            studentClass.setClassId(classId);
            studentClass.setStudentId(studentEntity.getStudentId());
            studentClassList.add(studentClass);
        });
        studentClassDao.insertBatch(studentClassList);

    }
}
