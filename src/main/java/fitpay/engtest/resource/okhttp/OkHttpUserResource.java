package fitpay.engtest.resource.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import fitpay.engtest.entity.CreditCard;
import fitpay.engtest.entity.CreditCardPaginatedListDto;
import fitpay.engtest.entity.Device;
import fitpay.engtest.entity.DevicePaginatedListDto;
import fitpay.engtest.entity.User;
import fitpay.engtest.entity.UserPaginatedListDto;
import fitpay.engtest.error.ServiceException;
import fitpay.engtest.error.UserNotFoundException;
import fitpay.engtest.resource.UserResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("okHttpUserResource")
public class OkHttpUserResource implements UserResource {
  private static final Logger logger = LoggerFactory.getLogger(OkHttpUserResource.class);

  private final OkHttpClient httpClient;
  private final ObjectMapper objectMapper;
  private final String baseUrl;

  @Autowired
  public OkHttpUserResource(ObjectMapper objectMapper, OkHttpClient httpClient, @Value("${fitPayApiUrl}") String baseUrl) {
    this.objectMapper = objectMapper;
    this.httpClient = httpClient;
    this.baseUrl = baseUrl;
  }

  @Override
  public User getUser(String id) {
    Request request = new Request.Builder()
        .url(baseUrl + "/users/" + id)
        .build();

    try {
      return executeRequest(request, User.class);
    } catch (ServiceException ex) {
      if (ex.getStatus() == HttpStatus.NOT_FOUND) {
        throw new UserNotFoundException("User "+ id + " is not found.");
      }

      throw ex;
    }
  }

  @Override
  public List<Device> getAllDevices(User user) {
    DevicePaginatedListDto devicePaginatedListDto = getDevices(user);

    if(devicePaginatedListDto.results().isEmpty()) {
      return new ArrayList<>();
    }

    List<Device> devices = devicePaginatedListDto.results();

    while(devicePaginatedListDto.links().containsKey("next")) {
      Request request = new Request.Builder()
          .url(devicePaginatedListDto.links().get("next").href())
          .build();

      devicePaginatedListDto = executeRequest(request, DevicePaginatedListDto.class);

      devices.addAll(devicePaginatedListDto.results());
    }

    return devices;
  }

  @Override
  public DevicePaginatedListDto getDevices(User user) {
    Request request = new Request.Builder()
        .url(user.links().get("devices").href())
        .build();

    return executeRequest(request, DevicePaginatedListDto.class);
  }

  @Override
  public List<CreditCard> getAllCreditCards(User user) {
    CreditCardPaginatedListDto creditCardPaginatedListDto = getCreditCards(user);

    if(creditCardPaginatedListDto.results().isEmpty()) {
      return new ArrayList<>();
    }

    List<CreditCard> creditCards = creditCardPaginatedListDto.results();

    while(creditCardPaginatedListDto.links().containsKey("next")) {
      Request request = new Request.Builder()
          .url(creditCardPaginatedListDto.links().get("next").href())
          .build();

      creditCardPaginatedListDto = executeRequest(request, CreditCardPaginatedListDto.class);

      creditCards.addAll(creditCardPaginatedListDto.results());
    }

    return creditCards;
  }

  @Override
  public CreditCardPaginatedListDto getCreditCards(User user) {
    Request request = new Request.Builder()
        .url(user.links().get("creditCards").href())
        .build();

    return executeRequest(request, CreditCardPaginatedListDto.class);
  }

  /**
   * Executes a {@link Request} and maps the response into a given responseType.
   *
   * @param request a {@link Request} to execute.
   * @param responseType {@link Class} to map the result body into.
   * @return A mapped response.
   * @throws ServiceException if {@link Response#isSuccessful()} is false, or the call doesn't complete.
   */
  private <T> T executeRequest(Request request, Class<T> responseType) throws ServiceException {
    try (Response response = httpClient.newCall(request).execute()) {
      if (response.isSuccessful()) {
        return mapResponse(response.body(), responseType);
      } else {
        HttpStatus httpStatus = HttpStatus.valueOf(response.code());
        // Raise a general exception
        throw new ServiceException(httpStatus);
      }
    } catch (IOException exception) {
      logger.error("There was an error when completing a request to an external service", exception);

      throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "A request to an external service failed.");
    }
  }

  /**
   * Maps a {@link ResponseBody} to a given responseType.
   *
   * @param responseBody {@link ResponseBody} to map.
   * @param responseType {@link Class} to map into.
   * @return A mapped object.
   * @throws RuntimeException if there is a mapping issue.
   */
  private <T> T mapResponse(ResponseBody responseBody, Class<T> responseType) throws RuntimeException {
    try {
      return objectMapper.readValue(responseBody.string(), responseType);
    } catch (IOException exception) {
      logger.error("There was an error with mapping the response body", exception);

      throw new RuntimeException("Unable to map response body.");
    }
  }

}
