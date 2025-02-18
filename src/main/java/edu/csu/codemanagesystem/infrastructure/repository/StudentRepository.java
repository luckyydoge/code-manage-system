package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.student.repository.IStudentRepository;
import edu.csu.codemanagesystem.infrastructure.dao.IStudentDao;
import edu.csu.codemanagesystem.infrastructure.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {

    @Autowired
    IStudentDao studentDao;

    @Override
    public List<StudentEntity> queryStudentByFactor(StudentEntity factor) {
        Student req = new Student();
        req.setStudentId(factor.getStudentId());
        req.setName(factor.getName());
        req.setEmail(factor.getEmail());
        req.setSex(factor.getSex());
        req.setPhoneNumber(factor.getPhoneNumber());
        req.setEmail(factor.getEmail());
        req.setAdministrativeClass(factor.getAdministrativeClass());
        List<Student> studentList = studentDao.queryStudentByFactor(req);
        List<StudentEntity> studentEntityList = new ArrayList<>();
        studentList.forEach(student-> {
            StudentEntity studentEntity = StudentEntity.builder()
                    .phoneNumber(student.getPhoneNumber())
                    .administrativeClass(student.getAdministrativeClass())
                    .sex(student.getSex())
                    .studentId(student.getStudentId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .build();
            studentEntityList.add(studentEntity);
        });
        return studentEntityList;
    }
}
