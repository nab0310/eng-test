package fitpay.engtest.resource.okhttp;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import fitpay.engtest.entity.User;
import fitpay.engtest.error.ServiceException;
import fitpay.engtest.error.UserNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class OkHttpUserResourceSpec {
  private static final MockWebServer mockWebServer = new MockWebServer();
  private static OkHttpUserResource okHttpUserResource;

  @BeforeAll
  static void setup() throws IOException {
    mockWebServer.start();
    okHttpUserResource = new OkHttpUserResource(new ObjectMapper(), new OkHttpClient(), mockWebServer.url("/").toString());
  }

  @AfterAll
  static void shutDown() throws IOException {
    mockWebServer.shutdown();
  }

  @Nested
  @DisplayName("#getUser")
  class GetUser {
    @Nested
    class when_service_is_unhealthy {
      @Test
      void returns_service_error() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(503));

        assertThrows(ServiceException.class, () -> okHttpUserResource.getUser("1234"));
      }
    }

    @Nested
    class when_service_is_healthy {
      @Test
      void when_id_is_unknown_throws_UserNotFoundException() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        assertThrows(UserNotFoundException.class, () -> okHttpUserResource.getUser("1234"));
      }

      @Test
      void when_id_is_known_returns_User_object() {
        String body = "{"
            + "    \"_links\": {},"
            + "    \"id\": \"329b3609-5bbf-4b17-91ed-1534ff05ec3a\","
            + "    \"createdTs\": \"2020-11-05T21:20:00.135Z\",\n"
            + "    \"createdTsEpoch\": 1604611200135,\n"
            + "    \"lastModifiedTs\": \"2020-11-05T21:20:00.135Z\",\n"
            + "    \"lastModifiedTsEpoch\": 1604611200135,"
            + "    \"userId\": \"329b3609-5bbf-4b17-91ed-1534ff05ec3a\"}";

        User expected = User.builder()
            .links(new HashMap<>())
            .id("329b3609-5bbf-4b17-91ed-1534ff05ec3a")
            .createdTs("2020-11-05T21:20:00.135Z")
            .createdTsEpoch(1604611200135L)
            .lastModifiedTs("2020-11-05T21:20:00.135Z")
            .lastModifiedTsEpoch(1604611200135L)
            .userId("329b3609-5bbf-4b17-91ed-1534ff05ec3a").build();

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(body));

        assertEquals(expected, okHttpUserResource.getUser("1234"));
      }
    }
  }
}
