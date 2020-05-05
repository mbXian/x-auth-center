package com.xmb.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Ben
 * @date 2020-05-04
 * @desc
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.xmb.*"})
public class AuthcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthcenterApplication.class, args);
    }
}
