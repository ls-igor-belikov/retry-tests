package com.example.retrytests;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException.InternalServerError;
import reactor.util.retry.Retry;

@Service
public class SimpleService {

  private final Retry retry = Retry.max(2)
      .filter(InternalServerError.class::isInstance)
      .doAfterRetry(retrySignal -> System.out.printf("retry message: %s, attempt: %s%n", retrySignal.failure().getMessage(), retrySignal.totalRetries()));

  private ApiClientService apiClientService;

  public SimpleService(ApiClientService apiClientService) {
    this.apiClientService = apiClientService;
  }

  public ResponseEntity<String> sendRequest() {
    return apiClientService.get().retryWhen(retry).block();
  }
}
