package edu.csu.codemanagesystem.domain.admin.service.semester;

import edu.csu.codemanagesystem.domain.admin.model.entity.SemesterEntity;
import edu.csu.codemanagesystem.domain.admin.repository.IAdminRepository;
import edu.csu.codemanagesystem.domain.admin.service.ISemesterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SemesterServiceService implements ISemesterService {

    final private IAdminRepository semesterRepository;

    public SemesterServiceService(IAdminRepository semesterRepository) {
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

    @Override
    public SemesterEntity queryCurrentSemester() {
        SemesterEntity semesterEntity = semesterRepository.queryCurrentSemester();
        return semesterEntity;
    }

    @Override
    public List<SemesterEntity> querySemesterByFactor(SemesterEntity semesterEntity) {
        List<SemesterEntity> semesterEntityList = semesterRepository.querySemesterByFactor(semesterEntity);
        return semesterEntityList;
    }
}
