package com.codingapi.example.client.service;

import com.codingapi.example.client.mapper.ClientDemoMapper;
import com.codingapi.example.common.db.domain.Demo;
import com.codingapi.example.common.spring.DDemoClient;
import com.codingapi.example.common.spring.EDemoClient;
import com.codingapi.txlcn.common.util.Transactions;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Service
@Order(Integer.MIN_VALUE)
public class DemoServiceImpl implements DemoService {

    private final ClientDemoMapper demoMapper;

    private final DDemoClient dDemoClient;

    private final EDemoClient eDemoClient;

    @Autowired
    public DemoServiceImpl(ClientDemoMapper demoMapper, DDemoClient dDemoClient, EDemoClient eDemoClient) {
        this.demoMapper = demoMapper;
        this.dDemoClient = dDemoClient;
        this.eDemoClient = eDemoClient;
    }

    @Override
    @LcnTransaction
    public String execute(String value) {

        String eResp = executeE(value);
        String dResp = dDemoClient.rpc(value);
        Demo demo = new Demo();
        demo.setDemoField(value);
        demo.setCreateTime(new Date());
        demo.setGroupId(TracingContext.tracing().groupId());
        demo.setAppName(Transactions.getApplicationId());
        demoMapper.save(demo);

//        int i = 1/0;
        return dResp + " > " + eResp + " > " + "ok-client";
    }

    private String executeE(String value){
        return eDemoClient.rpc(value);
    }

}
