package com.xmb.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Ben
 * @date 2020-05-04
 * @desc
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.xmb.*"})
@EnableFeignClients(basePackages = "com.xmb.*")
@EnableSwagger2
public class AuthcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthcenterApplication.class, args);
    }
}
