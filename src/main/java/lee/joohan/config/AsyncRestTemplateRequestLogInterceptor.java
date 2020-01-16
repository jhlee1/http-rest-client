package lee.joohan.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.AsyncClientHttpRequestExecution;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by Joohan Lee on 2019/12/18
 */

@Slf4j
public class AsyncRestTemplateRequestLogInterceptor implements AsyncClientHttpRequestInterceptor {
//  private static final Logger log = LoggerFactory.getLogger(AsyncRestTemplateRequestLogInterceptor.class);

  @Override
  public ListenableFuture<ClientHttpResponse> intercept(
      HttpRequest request,
      byte[] body,
      AsyncClientHttpRequestExecution execution) throws IOException {
    StringBuilder requestLog = new StringBuilder()
        .append("[HTTP Async Request] ")
        .append(request.getMethod())
        .append(" ")
        .append(request.getURI());

    if (body.length > 0) {
      requestLog
          .append(", Body: ")
          .append(new String(body, StandardCharsets.UTF_8));
    }

        log.info(requestLog.toString());

    return execution.executeAsync(request, body);
  }
}
