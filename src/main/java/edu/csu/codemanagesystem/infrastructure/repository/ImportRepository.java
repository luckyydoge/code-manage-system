package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.domain.import_excel.repository.IImportRepository;
import edu.csu.codemanagesystem.infrastructure.dao.ITeacherDao;
import edu.csu.codemanagesystem.infrastructure.dao.IUserDao;
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
    private IUserDao userDao;

    @Autowired
    private ITeacherDao teacherDao;

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
            user.setPassword("123456");
            userList.add(user);
        });

        userDao.insertUserBatch(userList);


    }
}
