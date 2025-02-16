package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.admin.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.admin.model.entity.SemesterEntity;
import edu.csu.codemanagesystem.domain.admin.repository.IAdminRepository;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;
import edu.csu.codemanagesystem.infrastructure.dao.ICourseDao;
import edu.csu.codemanagesystem.infrastructure.dao.ISemesterDao;
import edu.csu.codemanagesystem.infrastructure.dao.ITeacherDao;
import edu.csu.codemanagesystem.infrastructure.dao.IUserDao;
import edu.csu.codemanagesystem.infrastructure.po.Course;
import edu.csu.codemanagesystem.infrastructure.po.Semester;
import edu.csu.codemanagesystem.infrastructure.po.Teacher;
import edu.csu.codemanagesystem.infrastructure.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepository implements IAdminRepository {

    @Autowired
    IUserDao userDao;

    @Autowired
    ISemesterDao semesterDao;

    @Autowired
    ICourseDao courseDao;

    @Autowired
    ITeacherDao teacherDao;

    private long teacherBaseCount = 200000L;

    @Override
    public List<CourseEntity> queryCourseByFactor(CourseEntity courseReqFactor) {
        Course courseReq = new Course();
        courseReq.setCourseId(courseReqFactor.getCourseId());
        courseReq.setName(courseReqFactor.getName());
        courseReq.setStartTime(courseReqFactor.getStartTime());
        courseReq.setEndTime(courseReqFactor.getEndTime());
        List<Course> courseList = courseDao.queryCourseByFactor(courseReq);
        List<CourseEntity> courseEntityList = new ArrayList<>();
        courseList.forEach(course -> {
            CourseEntity courseEntity = CourseEntity.builder()
                    .courseId(course.getCourseId())
                    .name(course.getName())
                    .status(course.getStatus())
                    .startTime(course.getStartTime())
                    .endTime(course.getEndTime())
                    .build();
            courseEntityList.add(courseEntity);
        });

        return courseEntityList;
    }

    @Override
    public void createCourse(CourseEntity courseEntity) {
        Course course = new Course();
        course.setCourseId(courseEntity.getCourseId());
        course.setName(courseEntity.getName());
        course.setStartTime(courseEntity.getStartTime());
        course.setEndTime(courseEntity.getEndTime());
        courseDao.createCourse(course);
    }

    @Override
    public Boolean createTeacher(TeacherEntity teacherEntity) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherEntity.getName());
        teacher.setEmail(teacherEntity.getEmail());
        int count = teacherDao.queryTeacherCount();
        teacher.setTeacherId(count + teacherBaseCount);
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacher);
        teacherDao.insertTeacherBatch(teacherList);
        User user = new User();
        user.setUserId(teacher.getTeacherId());
        user.setType("teacher");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userDao.insertUserBatch(userList);
        return true;
    }

    @Override
    public List<SemesterEntity> queryAllSemester() {
        List<Semester> semesterList = semesterDao.queryAllSemester();
        List<SemesterEntity> semesterEntityList = new ArrayList<>();
        semesterList.forEach(semester -> {
            SemesterEntity semesterEntity = SemesterEntity.builder()
                    .semesterId(semester.getSemesterId())
                    .name(semester.getName())
                    .endTime(semester.getEndTime())
                    .startTime(semester.getStartTime())
                    .status(semester.getStatus())
                    .build();
            semesterEntityList.add(semesterEntity);
        });
        return semesterEntityList;
    }

    @Override
    public void createSemester(SemesterEntity semesterEntity) {
        Semester semester = new Semester();
        long count = semesterDao.querySemesterCount();
        semester.setSemesterId(count + 1);
        semester.setName(semesterEntity.getName());
        semester.setEndTime(semesterEntity.getEndTime());
        semester.setStartTime(semesterEntity.getStartTime());
        semesterDao.createSemester(semester);

    }

    @Override
    public Boolean setCurrentSemester(Long semesterId) {
        semesterDao.setCurrentSemester(semesterId);
        return true;
    }
}
