package org.hcmus.ln02;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Sakila RESTful API", version="1.0", description="Sakila Database CRUD with RESTful API"))
@EnableScheduling
public class Ln02Application {

  public static void main(String[] args) {
    SpringApplication.run(Ln02Application.class, args);
  }

}
