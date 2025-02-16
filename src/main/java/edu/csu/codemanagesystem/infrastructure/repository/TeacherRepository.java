package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.teacher.model.entity.ClassEntity;
import edu.csu.codemanagesystem.domain.teacher.repository.ITeacherRepository;
import edu.csu.codemanagesystem.infrastructure.dao.IClassDao;
import edu.csu.codemanagesystem.infrastructure.po.ClassPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository implements ITeacherRepository {

    @Autowired
    IClassDao classDao;

    @Override
    public void createClass(ClassEntity classEntity) {
        long count = classDao.queryClassCount();
        ClassPO classPo = new ClassPO();
        classPo.setName(classEntity.getName());
        classPo.setTeacherId(classEntity.getTeacherId());
        classPo.setSemesterId(classEntity.getSemesterId());
        classPo.setPeopleCount(classEntity.getPeopleCount());
        classPo.setCourseId(classEntity.getCourseId());
        classPo.setClassId(count + 400000 + 1);
        classDao.createClass(classPo);
    }
}
