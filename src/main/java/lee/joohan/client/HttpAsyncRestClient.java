package lee.joohan.client;

import java.util.Collections;
import lee.joohan.common.HttpRequest;
import lee.joohan.config.AsyncRestTemplateRequestLogInterceptor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

/**
 * Created by Joohan Lee on 2020/01/15
 */
public class HttpAsyncRestClient {
  private AsyncRestTemplate asyncRestTemplate;

  public HttpAsyncRestClient(String baseUrl) {
    asyncRestTemplate = new AsyncRestTemplate();

    asyncRestTemplate.setInterceptors(Collections.singletonList(new AsyncRestTemplateRequestLogInterceptor()));
    DefaultUriTemplateHandler defaultUriTemplateHandler = new DefaultUriTemplateHandler();

    defaultUriTemplateHandler.setBaseUrl(baseUrl);
    asyncRestTemplate.setUriTemplateHandler(defaultUriTemplateHandler);
    asyncRestTemplate.setInterceptors(Collections.singletonList(new AsyncRestTemplateRequestLogInterceptor()));
  }

  public <T> ListenableFuture<ResponseEntity<T>> get(HttpRequest httpRequest, Class<T> responseType) {
    return execute(httpRequest, HttpMethod.GET, responseType);
  }

  public <T> ListenableFuture<ResponseEntity<T>> get(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return execute(httpRequest, HttpMethod.GET, responseTypeRef);
  }

  public <T> ListenableFuture<ResponseEntity<T>> post(HttpRequest httpRequest, Class<T> responseType) {
    return execute(httpRequest, HttpMethod.POST, responseType);
  }

  public <T> ListenableFuture<ResponseEntity<T>> post(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return execute(httpRequest, HttpMethod.POST, responseTypeRef);
  }

  public <T> ListenableFuture<ResponseEntity<T>> put(HttpRequest httpRequest, Class<T> responseType) {
    return execute(httpRequest, HttpMethod.PUT, responseType);
  }

  public <T> ListenableFuture<ResponseEntity<T>> put(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return execute(httpRequest, HttpMethod.PUT, responseTypeRef);
  }

  public <T> ListenableFuture<ResponseEntity<T>> delete(HttpRequest httpRequest, Class<T> responseType) {
    return execute(httpRequest, HttpMethod.DELETE, responseType);
  }

  public <T> ListenableFuture<ResponseEntity<T>> delete(HttpRequest httpRequest, ParameterizedTypeReference<T> responseTypeRef) {
    return execute(httpRequest, HttpMethod.DELETE, responseTypeRef);
  }

  private <T> ListenableFuture<ResponseEntity<T>> execute(HttpRequest httpRequest, HttpMethod httpMethod, Class<T> responseType) {
    return asyncRestTemplate.exchange(httpRequest.getQueriedUrl(), httpMethod, httpRequest.toRequestEntity(), responseType);
  }

  private <T> ListenableFuture<ResponseEntity<T>> execute(HttpRequest httpRequest, HttpMethod httpMethod, ParameterizedTypeReference<T> responseType) {
    return asyncRestTemplate.exchange(httpRequest.getQueriedUrl(), httpMethod, httpRequest.toRequestEntity(), responseType);
  }
}
