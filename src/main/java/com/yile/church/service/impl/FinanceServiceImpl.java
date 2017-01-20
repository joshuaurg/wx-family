package com.yile.church.service.impl;

import com.yile.church.mapper.FinanceModelMapper;
import com.yile.church.model.FinanceModel;
import com.yile.church.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dcx on 17/1/20.
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceModelMapper financeModelMapper;

    @Override
    public void insert(FinanceModel finance) {
        financeModelMapper.insert(finance);
    }
}
