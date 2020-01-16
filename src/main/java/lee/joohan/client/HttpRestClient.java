package lee.joohan.client;

import java.util.Collections;
import lee.joohan.common.HttpRequest;
import lee.joohan.config.RestTemplateRequestLogInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Joohan Lee on 2020/01/15
 */
public class HttpRestClient {
  private RestTemplate restTemplate;
  private String baseUrl;

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
  }

  public <T> T get(HttpRequest httpRequest, Class<T> responseType) {
    return restTemplate.exchange(httpRequest.toRequestEntity(baseUrl, HttpMethod.GET), responseType).getBody();
  }

  public <T> T get(HttpRequest HttpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return restTemplate.exchange(HttpRequest.toRequestEntity(baseUrl, HttpMethod.GET), responseTypeRef).getBody();
  }

  public <T> T post(HttpRequest HttpRequest, Class<T> responseType) {
    return restTemplate.exchange(HttpRequest.toRequestEntity(baseUrl, HttpMethod.POST), responseType).getBody();
  }

  public <T> T post(HttpRequest HttpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return restTemplate.exchange(HttpRequest.toRequestEntity(baseUrl, HttpMethod.POST), responseTypeRef).getBody();
  }

  public <T> T put(HttpRequest HttpRequest, Class<T> responseType) {
    return restTemplate.exchange(HttpRequest.toRequestEntity(baseUrl, HttpMethod.PUT), responseType).getBody();
  }

  public <T> T put(HttpRequest HttpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return restTemplate.exchange(HttpRequest.toRequestEntity(baseUrl, HttpMethod.PUT), responseTypeRef).getBody();
  }

  public <T> T delete(HttpRequest HttpRequest, Class<T> responseType) {
    return restTemplate.exchange(HttpRequest.toRequestEntity(baseUrl, HttpMethod.DELETE), responseType).getBody();
  }

  public <T> T delete(HttpRequest HttpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return restTemplate.exchange(HttpRequest.toRequestEntity(baseUrl, HttpMethod.DELETE), responseTypeRef).getBody();
  }
}

