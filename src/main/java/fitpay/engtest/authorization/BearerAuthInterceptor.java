package fitpay.engtest.authorization;

import java.io.IOException;
import java.util.Objects;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BearerAuthInterceptor implements Interceptor {
  private static final String AUTHORIZATION_HEADER = "Authorization";
  private final String authorizationHeaderValue;

  /**
   * Creates a {@link BearerAuthInterceptor} instance.
   *
   * @param token Value for bearer token.
   */
  public BearerAuthInterceptor(String token) {
    Objects.requireNonNull(token, "token cannot be null");

    this.authorizationHeaderValue = "Bearer " + token;
  }

  /**
   * Intercepts an {@link OkHttpClient} call by computing and populating the authorization header
   * with a bearer token if it is not already populated.
   *
   * @param chain {@link Chain} to help with intercepting the http call.
   * @return The {@link Response} for the OAuth secured http call.
   * @throws IOException If errors were encountered during HTTP call execution.
   */
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();

    if (request.header(AUTHORIZATION_HEADER) == null) {
      request =
          request.newBuilder().addHeader(AUTHORIZATION_HEADER, authorizationHeaderValue).build();
    }
    return chain.proceed(request);
  }
}
