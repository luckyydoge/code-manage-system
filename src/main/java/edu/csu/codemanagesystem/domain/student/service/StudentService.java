package edu.csu.codemanagesystem.domain.student.service;

import edu.csu.codemanagesystem.domain.import_excel.model.StudentEntity;
import edu.csu.codemanagesystem.domain.student.IStudentService;
import edu.csu.codemanagesystem.domain.student.repository.IStudentRepository;
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
}
