package com.bhavana;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import java.io.*;

import java.math.BigDecimal;

@SpringBootApplication
public class StartEmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartEmployeeApplication.class, args);
    }

    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        Employee employee1 = new Employee("Zef","Gtf");
        Dependent dependent1 = new Dependent("Efg","Gtf");
        Dependent dependent2 = new Dependent("Weg","Gtf");
        Qualification qualification1 = new Qualification("Graduation");
        employee1.addDependent(dependent1);
        employee1.addDependent(dependent2);
        employee1.setQualification(qualification1);
        return args -> {
            repository.save(employee1);
            repository.save(new Employee("Abc","Efg"));
            repository.save(new Employee("Ghj","Lkm"));
            repository.save(new Employee("Vfc","Aec"));
        };
    }
}