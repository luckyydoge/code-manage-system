package edu.csu.codemanagesystem.infrastructure.dao;

import edu.csu.codemanagesystem.infrastructure.po.Semester;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISemesterDao {
    List<Semester> queryAllSemester();

    void createSemester(Semester semester);

    void setCurrentSemester(Long semesterId);
}
