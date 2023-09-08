package com.doubantop;

import com.doubantop.service.impl.MovieServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.doubantop.mapper")
public class DouabnCrawlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DouabnCrawlerApplication.class, args);
    }
}
