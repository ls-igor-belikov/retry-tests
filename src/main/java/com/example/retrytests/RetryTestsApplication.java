package com.example.retrytests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class RetryTestsApplication {

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .baseUrl("http://google.com")
        .build();
  }

  public static void main(String[] args) {
    SpringApplication.run(RetryTestsApplication.class, args);
  }

}
