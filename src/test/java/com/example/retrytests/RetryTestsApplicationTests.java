package com.example.retrytests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException.InternalServerError;
import reactor.core.publisher.Mono;

@SpringBootTest
class RetryTestsApplicationTests {

  @MockBean
  ApiClientService apiClientService;

  @Autowired
  SimpleService simpleService;

  @Test
  void contextLoads() {
  }

  @Test
  void testRetry() {
    when(apiClientService.get())
        .thenReturn(Mono.error(InternalServerError.create(500, "error", null, null, null)))
        .thenReturn(Mono.just(
        ResponseEntity.ok("test passed")));

    ResponseEntity<String> response = simpleService.sendRequest();

    assertEquals("test passed", response.getBody());
  }
}
