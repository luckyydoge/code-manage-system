package edu.csu.codemanagesystem.domain.semester.service;

import edu.csu.codemanagesystem.domain.semester.model.entity.SemesterEntity;
import edu.csu.codemanagesystem.domain.semester.repository.ISemesterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SemesterService implements ISemester{

    final private ISemesterRepository semesterRepository;

    public SemesterService(ISemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    public List<SemesterEntity> queryAllSemester() {
        List<SemesterEntity> semesterEntityList = semesterRepository.queryAllSemester();
        log.info("query all semester");
        return semesterEntityList;
    }

    @Override
    public Boolean createSemester(SemesterEntity semester) {
//        if (semester.getName().isBlank()
//        || semester.getSemesterId() == null
//        || semester.getStartTime() == null
//        || semester.getEndTime() == null) {
//            return false;
//        }
        semesterRepository.createSemester(semester);
        return true;
    }

    @Override
    public Boolean setCurrentSemester(Long semesterId) {
        Boolean success = semesterRepository.setCurrentSemester(semesterId);
        return success;
    }
}
