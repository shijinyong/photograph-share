package com.photograph.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: shijinyong
 * @Date: 2023/2/17 16:18
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.photograph.system.mapper")
public class PhotographShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotographShareApplication.class);

    }

}
