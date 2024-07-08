package com.example.cs304;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Cs304Application {

    public static void main(String[] args) {
        SpringApplication.run(Cs304Application.class, args);
    }

}
