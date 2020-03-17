package lee.joohan.client;


import lee.joohan.common.HttpRequest;
import lee.joohan.dto.CreateCustomerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Joohan Lee on 2020/01/10
 */

@ExtendWith(SpringExtension.class)
class HttpRestClientTest {
  private final String baseUrl = "http://localhost:8080";
  private HttpRestClient httpRestClient;

  @BeforeEach
  void setUp() {
    httpRestClient = new HttpRestClient(baseUrl);
  }

  @Test
  void getWithoutParams() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers");

    ResponseEntity<String> result = httpRestClient.get(httpRequest, String.class);

    System.out.println("The response: " + result);
  }

  @Test
  void getWithParams() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers")
        .queryParams("firstName", "John");

    ResponseEntity<String> result = httpRestClient.get(httpRequest, String.class);

    System.out.println("The result: " + result);
  }

  @Test
  public void post() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers")
        .requestBody(new CreateCustomerRequest("first", "last", 10));

    ResponseEntity<String> response = httpRestClient.post(httpRequest, new ParameterizedTypeReference<String>() {});

    System.out.println("The response: " + response);
  }

  @Test
  public void delete() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers/{customerId}")
        .pathVariables("c572341e-77ca-4921-a316-674e0ff888a7");

    ResponseEntity<String> response = httpRestClient.delete(httpRequest, new ParameterizedTypeReference<String>() {});

    System.out.println("The response: " + response);
  }
}
