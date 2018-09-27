package org.ayaic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.ayaic.dao")
@SpringBootApplication
public class Hospitalsb2Application {

    public static void main(String[] args) {
        SpringApplication.run(Hospitalsb2Application.class, args);
    }
}
