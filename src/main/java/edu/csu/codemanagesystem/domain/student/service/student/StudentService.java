package edu.csu.codemanagesystem.domain.student.service.student;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.student.repository.IStudentRepository;
import edu.csu.codemanagesystem.domain.student.service.IStudentService;
import edu.csu.codemanagesystem.domain.teacher.model.entity.StudentJobEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentEntity> queryStudentByFactor(StudentEntity factor) {
        return studentRepository.queryStudentByFactor(factor);
    }

    @Override
    public List<StudentJobEntity> queryStudentJobByFactor(StudentJobEntity factor) {
        return studentRepository.queryStudentJobByFactor(factor);
    }
}
