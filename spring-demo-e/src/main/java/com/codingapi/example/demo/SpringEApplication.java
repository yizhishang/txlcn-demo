package com.codingapi.example.demo;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDistributedTransaction
public class SpringEApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEApplication.class, args);
    }
}
