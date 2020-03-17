package lee.joohan.common;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Joohan Lee on 2020/01/15
 */
public class HttpRequest {
  private String url;
  private MultiValueMap<String, String> headers;
  private Object[] pathVariables;
  private MultiValueMap<String, String> queryParams;
  private Object requestBody;

  public static HttpRequest builder() {
    return new HttpRequest();
  }

  public HttpRequest url(String url) {
    this.url = url;
    return this;
  }

  public HttpRequest headers(String key, String value) {
    if (headers == null) {
      headers = new LinkedMultiValueMap<>();
    }

    headers.set(key, value);
    return this;
  }

  public HttpRequest headers(Map<String, String> headers) {
    if (headers == null) {
      this.headers = new LinkedMultiValueMap<>();
    }

    this.headers.setAll(headers);
    return this;
  }

  public HttpRequest pathVariables(Object... pathVariables) {
    this.pathVariables = pathVariables;
    return this;
  }

  public HttpRequest queryParams(String key, String value) {
    if (queryParams == null) {
      queryParams = new LinkedMultiValueMap<>();
    }

    queryParams.set(key, value);
    return this;
  }

  public HttpRequest queryParams(HttpQueryParam httpQueryParams) {
    this.queryParams = httpQueryParams.toMultiValueMap();
    return this;
  }

  public HttpRequest requestBody(Object requestBody) {
    this.requestBody = requestBody;
    return this;
  }

  public RequestEntity toRequestEntity(String baseUrl, HttpMethod httpMethod) {
    URI queriedUri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(url)
        .queryParams(queryParams)
        .buildAndExpand(Optional.ofNullable(pathVariables).orElse(new Object[0]))
        .toUri();
    return new RequestEntity(requestBody, headers, httpMethod, queriedUri);
  }

  public RequestEntity toRequestEntity() {
    return new RequestEntity(requestBody, headers, null, null);
  }

  public HttpEntity getHttpEntity() {
    return new HttpEntity(requestBody, headers);
  }

  public String getQueriedUrl() {
    return UriComponentsBuilder
        .fromPath(url)
        .queryParams(queryParams)
        .buildAndExpand(Optional.ofNullable(pathVariables).orElse(new Object[0]))
        .toUriString();
  }
}
