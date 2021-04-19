package org.vaccine.world.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.vaccine.world")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
