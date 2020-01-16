package lee.joohan.client;


import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import lee.joohan.common.HttpRequest;
import lee.joohan.dto.CreateCustomerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by Joohan Lee on 2020/01/10
 */

@ExtendWith(SpringExtension.class)
class HttpAsyncRestClientTest {
  private final String baseUrl = "http://localhost:8080";
  private HttpAsyncRestClient httpAsyncRestClient;

  @BeforeEach
  void setUp() {
    httpAsyncRestClient = new HttpAsyncRestClient(baseUrl);
  }

  @Test
  void getWithoutParams() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers");

    ListenableFuture<ResponseEntity<String>> result = httpAsyncRestClient.get(httpRequest, String.class);

    try {
      System.out.println("The response: " + result.get().getBody());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Test
  void getWithParams() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers")
        .queryParams("firstName", "John");

    ListenableFuture<ResponseEntity<String>> result = httpAsyncRestClient.get(httpRequest, String.class);

    try {
      System.out.println("The response: " + result.get().getBody());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void post() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers")
        .requestBody(new CreateCustomerRequest("first", "last", 10));

    ListenableFuture<ResponseEntity<String>> response = httpAsyncRestClient.post(httpRequest, new ParameterizedTypeReference<String>() {});

    try {
      System.out.println("The response: " + response.get().getBody());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void put() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers/{userId}")
        .requestBody(new CreateCustomerRequest("EditedFirst", "Editedlast", 11))
        .pathVariables("6418b98f-f22d-4e09-969b-523dd80a8b51");

    ListenableFuture<ResponseEntity<String>> response = httpAsyncRestClient.put(httpRequest, new ParameterizedTypeReference<String>() {});

    try {
      System.out.println("The response: " + response.get().getBody());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void delete() {
    HttpRequest httpRequest = HttpRequest.builder()
        .url("/customers/{userId}")
        .pathVariables("be5fa3c-2fbd-4ced-a40b-11b8ef2c4534");

    ListenableFuture<ResponseEntity<String>> response = httpAsyncRestClient.delete(httpRequest, new ParameterizedTypeReference<String>() {});

    try {
      System.out.println("The response: " + response.get().getBody());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
