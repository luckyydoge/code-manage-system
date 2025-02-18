package edu.csu.codemanagesystem.domain.admin.repository;

import edu.csu.codemanagesystem.domain.admin.model.entity.CourseEntity;
import edu.csu.codemanagesystem.domain.admin.model.entity.SemesterEntity;
import edu.csu.codemanagesystem.domain.import_excel.model.TeacherEntity;

import java.util.List;

public interface IAdminRepository {
    List<SemesterEntity> queryAllSemester();

    void createSemester(SemesterEntity semester);

    Boolean setCurrentSemester(Long semesterId);

    List<CourseEntity> queryCourseByFactor(CourseEntity courseReqFactor);

    void createCourse(CourseEntity course);

    Boolean createTeacher(TeacherEntity teacherEntity);

    SemesterEntity queryCurrentSemester();

    List<TeacherEntity> queryAllTeachers();

    List<CourseEntity> queryUpcomingCourse(CourseEntity courseReqFactor);

    List<CourseEntity> queryOngoningCourse(CourseEntity courseReqFactor);

    List<CourseEntity> queryCompletedCourse(CourseEntity courseReqFactor);

    long queryCourseCount();
}
