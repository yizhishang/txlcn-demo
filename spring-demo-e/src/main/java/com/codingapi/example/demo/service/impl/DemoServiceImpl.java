package com.codingapi.example.demo.service.impl;

import com.codingapi.example.common.db.domain.Demo;
import com.codingapi.example.demo.mapper.EDemoMapper;
import com.codingapi.example.demo.service.DemoService;
import com.codingapi.txlcn.common.util.Transactions;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final EDemoMapper demoMapper;

    @Autowired
    public DemoServiceImpl(EDemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }

    @Override
    @LcnTransaction
    public String rpc(String value) {
        Demo demo = new Demo();

        demo.setDemoField(value);
        demo.setCreateTime(new Date());
        demo.setGroupId(TracingContext.tracing().groupId());
        demo.setAppName(Transactions.getApplicationId());
        demoMapper.save(demo);

//        int i = 1/0;
        return "ok-e";
    }

}
