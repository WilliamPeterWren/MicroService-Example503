package com.trannubichthai.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.File;
import static com.trannubichthai.userservice.constant.FileConstant.USER_FOLDER;

@SpringBootApplication(scanBasePackages = {
        "com.trannubichthai.userservice",
        "com.trannubichthai.amqp"
})
public class UserserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
        new File(USER_FOLDER).mkdirs();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}