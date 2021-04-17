package org.vaccine.world.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.vaccine.world.application.controller.VaccineController;

@SpringBootApplication
@ComponentScan(basePackageClasses= VaccineController.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
