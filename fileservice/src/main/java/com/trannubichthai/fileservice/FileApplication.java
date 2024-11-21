package com.trannubichthai.fileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import java.io.File;
import static com.trannubichthai.fileservice.constant.FileConstant.IMAGE_FOLDER;

@SpringBootApplication
// @EnableEurekaClient
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
        new File(IMAGE_FOLDER).mkdirs();
    }
}