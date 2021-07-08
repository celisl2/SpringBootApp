package com.example.springbootapp.repository;

import com.example.springbootapp.model.Student;
import com.example.springbootapp.model.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StudentRepository repository){
        return args -> {
            log.info("Loading Student: [{}]", repository.save(new Student("Navleen", 5)));
            log.info("Loading Student: [{}]", repository.save(new Student("Courtney", 25)));
            log.info("Loading Student: [{}]", repository.save(new Student("Laura", 54)));
        };
    }
}
