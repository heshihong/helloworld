package com.hsh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.hsh.user.mapper")
@SpringBootApplication
public class HeshihongMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeshihongMainApplication.class,args);
    }


}
