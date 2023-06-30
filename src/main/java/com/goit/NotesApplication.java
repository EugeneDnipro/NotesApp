package com.goit;

import com.goit.Conf.FlywayConfigurations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories("com/goit/Repository")
public class NotesApplication {

    public static void main(String[] args) throws IOException {
        new FlywayConfigurations().setup().migrate();
        SpringApplication.run(NotesApplication.class, args);
    }
}
