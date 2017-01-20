package com.yile.church.mapper;

import com.yile.church.model.FinanceModel;

public interface FinanceModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceModel record);

    int insertSelective(FinanceModel record);

    FinanceModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceModel record);

    int updateByPrimaryKey(FinanceModel record);
}