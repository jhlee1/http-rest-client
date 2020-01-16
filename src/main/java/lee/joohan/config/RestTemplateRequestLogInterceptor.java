package lee.joohan.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * Created by Joohan Lee on 2020/01/15
 */

@Slf4j
public class RestTemplateRequestLogInterceptor implements ClientHttpRequestInterceptor {
//  private static final Logger log = LoggerFactory.getLogger(RestTemplateRequestLogInterceptor.class);

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request,
      byte[] body,
      ClientHttpRequestExecution execution) throws IOException {

    StringBuilder requestLog = new StringBuilder()
        .append("[HTTP Request] ")
        .append(request.getMethod())
        .append(" ")
        .append(request.getURI());

    if (body.length > 0) {
      requestLog
          .append(", Body: ")
          .append(new String(body, StandardCharsets.UTF_8));
    }

    log.info(requestLog.toString());

    return execution.execute(request, body);
  }
}




