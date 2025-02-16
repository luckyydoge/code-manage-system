package edu.csu.codemanagesystem.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICurrentSemesterDao {
    long queryCurrentSemesterId();
}
