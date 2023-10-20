package com.example.retrytests;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiClientService {

  private final WebClient webClient;

  public ApiClientService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<ResponseEntity<String>> get() {
    return webClient.get().retrieve().toEntity(String.class);
  }
}
