package com.guli.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author helen
 * @since 2020/4/1
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.guli"})
public class ServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }
}