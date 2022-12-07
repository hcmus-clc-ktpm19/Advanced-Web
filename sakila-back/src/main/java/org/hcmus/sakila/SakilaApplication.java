package org.hcmus.sakila;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Sakila RESTful API", version = "1.0", description = "Sakila Database CRUD with RESTful API"))
public class SakilaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SakilaApplication.class, args);
  }

}
