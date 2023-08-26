package com.cydeo.streampractice;

import com.cydeo.streampractice.practice.Practice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamPracticeApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StreamPracticeApplication.class, args);
        System.out.println(Practice.getMaxSalary());
    }

}
