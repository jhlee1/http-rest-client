package lee.joohan.client;

import java.util.Collections;
import lee.joohan.common.HttpRequest;
import lee.joohan.config.AsyncRestTemplateRequestLogInterceptor;
import lee.joohan.config.RestTemplateRequestLogInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

/**
 * Created by Joohan Lee on 2020/01/15
 */
public class HttpRestClient {
  private final AsyncRestTemplate asyncRestTemplate;
  private final RestTemplate restTemplate;
  private final String baseUrl;

  public HttpRestClient(String baseUrl) {
    this.baseUrl = baseUrl;

    HttpClient client = HttpClientBuilder.create()
        .setMaxConnTotal(20)
        .setMaxConnPerRoute(20)
        .build();

    HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(client);

    httpRequestFactory.setConnectTimeout(5000);
    httpRequestFactory.setReadTimeout(20000);

    restTemplate = new RestTemplate(httpRequestFactory);
    restTemplate.setInterceptors(Collections.singletonList(new RestTemplateRequestLogInterceptor()));

    asyncRestTemplate = new AsyncRestTemplate();

    asyncRestTemplate.setInterceptors(Collections.singletonList(new AsyncRestTemplateRequestLogInterceptor()));
    DefaultUriTemplateHandler defaultUriTemplateHandler = new DefaultUriTemplateHandler();

    defaultUriTemplateHandler.setBaseUrl(baseUrl);
    asyncRestTemplate.setUriTemplateHandler(defaultUriTemplateHandler);
    asyncRestTemplate.setInterceptors(Collections.singletonList(new AsyncRestTemplateRequestLogInterceptor()));
  }

  public <T> ResponseEntity<T> get(HttpRequest httpRequest, Class<T> responseType) {
    return execute(httpRequest, HttpMethod.GET, responseType);
  }

  public <T> ResponseEntity<T> get(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return execute(httpRequest, HttpMethod.GET, responseTypeRef);
  }

  public <T> ResponseEntity<T> post(HttpRequest httpRequest, Class<T> responseType) {
    return execute(httpRequest, HttpMethod.POST, responseType);
  }

  public <T> ResponseEntity<T> post(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return execute(httpRequest, HttpMethod.POST, responseTypeRef);
  }

  public <T> ResponseEntity<T> put(HttpRequest httpRequest, Class<T> responseType) {
    return execute(httpRequest, HttpMethod.PUT, responseType);
  }

  public <T> ResponseEntity<T> put(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return execute(httpRequest, HttpMethod.PUT, responseTypeRef);
  }

  public <T> ResponseEntity<T> delete(HttpRequest httpRequest, Class<T> responseType) {
    return execute(httpRequest, HttpMethod.DELETE, responseType);
  }

  public <T> ResponseEntity<T> delete(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return execute(httpRequest, HttpMethod.DELETE, responseTypeRef);
  }

  public <T> ListenableFuture<ResponseEntity<T>> getAsync(HttpRequest httpRequest, Class<T> responseType) {
    return executeAsync(httpRequest, HttpMethod.GET, responseType);
  }

  public <T> ListenableFuture<ResponseEntity<T>> getAsync(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return executeAsync(httpRequest, HttpMethod.GET, responseTypeRef);
  }

  public <T> ListenableFuture<ResponseEntity<T>> postAsync(HttpRequest httpRequest, Class<T> responseType) {
    return executeAsync(httpRequest, HttpMethod.POST, responseType);
  }

  public <T> ListenableFuture<ResponseEntity<T>> postAsync(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return executeAsync(httpRequest, HttpMethod.POST, responseTypeRef);
  }

  public <T> ListenableFuture<ResponseEntity<T>> putAsync(HttpRequest httpRequest, Class<T> responseType) {
    return executeAsync(httpRequest, HttpMethod.PUT, responseType);
  }

  public <T> ListenableFuture<ResponseEntity<T>> putAsync(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return executeAsync(httpRequest, HttpMethod.PUT, responseTypeRef);
  }

  public <T> ListenableFuture<ResponseEntity<T>> deleteAsync(HttpRequest httpRequest, Class<T> responseType) {
    return executeAsync(httpRequest, HttpMethod.DELETE, responseType);
  }

  public <T> ListenableFuture<ResponseEntity<T>> deleteAsync(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return executeAsync(httpRequest, HttpMethod.DELETE, responseTypeRef);
  }

  private <T> ResponseEntity<T> execute(HttpRequest httpRequest, HttpMethod httpMethod, ParameterizedTypeReference<T> responseTypeRef) {
    return restTemplate.exchange(httpRequest.toRequestEntity(baseUrl, httpMethod), responseTypeRef);
  }

  private <T> ResponseEntity<T> execute(HttpRequest httpRequest, HttpMethod httpMethod, Class<T> responseType) {
    return restTemplate.exchange(httpRequest.toRequestEntity(baseUrl, httpMethod), responseType);
  }

  private <T> ListenableFuture<ResponseEntity<T>> executeAsync(HttpRequest httpRequest, HttpMethod httpMethod, Class<T> responseType) {
    return asyncRestTemplate.exchange(httpRequest.getQueriedUrl(), httpMethod, httpRequest.getHttpEntity(), responseType);
  }

  private <T> ListenableFuture<ResponseEntity<T>> executeAsync(HttpRequest httpRequest, HttpMethod httpMethod, ParameterizedTypeReference<T> responseTypeRef) {
    return asyncRestTemplate.exchange(httpRequest.getQueriedUrl(), httpMethod, httpRequest.getHttpEntity(), responseTypeRef);
  }
}

