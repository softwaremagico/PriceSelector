package com.test.bcnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
public class TestServer {
    public static void main(String[] args) {
        SpringApplication.run(TestServer.class, args);
    }
}
