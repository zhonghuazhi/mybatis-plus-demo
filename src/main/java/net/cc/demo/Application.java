package net.cc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author
 * @version 1.0
 * @classname Application
 * @date 2020-01-07 00:13
 * @description TODO
 */
@SpringBootApplication
@MapperScan("net.cc.demo.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}