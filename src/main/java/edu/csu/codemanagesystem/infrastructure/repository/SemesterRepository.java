package edu.csu.codemanagesystem.infrastructure.repository;

import edu.csu.codemanagesystem.domain.semester.model.entity.SemesterEntity;
import edu.csu.codemanagesystem.domain.semester.repository.ISemesterRepository;
import edu.csu.codemanagesystem.infrastructure.dao.ISemesterDao;
import edu.csu.codemanagesystem.infrastructure.po.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SemesterRepository implements ISemesterRepository {

    @Autowired
    ISemesterDao semesterDao;

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
        semester.setSemesterId(semesterEntity.getSemesterId());
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
