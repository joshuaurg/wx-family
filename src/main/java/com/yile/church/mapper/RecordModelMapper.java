package com.yile.church.mapper;

import com.yile.church.model.RecordModel;

public interface RecordModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordModel record);

    int insertSelective(RecordModel record);

    RecordModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecordModel record);

    int updateByPrimaryKey(RecordModel record);
}