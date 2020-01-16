package lee.joohan.common;
    
import java.util.Objects;
import java.util.Optional;
import lee.joohan.common.QueryParamKey;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * Created by Joohan Lee on 2020/01/15
 */
public class HttpQueryParam {
  private MultiValueMap<String, String> queryParams;

  public HttpQueryParam() {
    this.queryParams = new LinkedMultiValueMap<>();
  }

  public static HttpQueryParam builder() {
    return new HttpQueryParam();
  }

  public HttpQueryParam stringParam(String stringExample) {
    Optional.ofNullable(stringExample)
        .filter(StringUtils::isEmpty)
        .ifPresent(example -> queryParams.add(QueryParamKey.STRING, example));

    return this;
  }

  public HttpQueryParam intParam(int intExample) {
    queryParams.add(QueryParamKey.INT, String.valueOf(intExample));

    return this;
  }

  public HttpQueryParam longParam(long longExample) {
    queryParams.add(QueryParamKey.LONG, String.valueOf(longExample));

    return this;
  }

  public HttpQueryParam objectParam(Object objectExample) {
    Optional.ofNullable(objectExample)
        .filter(Objects::nonNull)
        .map(Object::toString)
        .ifPresent(example -> queryParams.add(QueryParamKey.OBJECT, example));

    return this;
  }

  public MultiValueMap toMultiValueMap() {
    return queryParams;
  }
}