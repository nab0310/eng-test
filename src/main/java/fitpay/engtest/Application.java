package fitpay.engtest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fitpay.engtest.authorization.BearerAuthInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${bearerToken}")
    private String BEARER_TOKEN;

    @Autowired
    @Bean
    public OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder()
        .addInterceptor(new BearerAuthInterceptor(BEARER_TOKEN))
        .connectTimeout(6000, TimeUnit.MILLISECONDS)
        .writeTimeout(6000, TimeUnit.MILLISECONDS)
        .readTimeout(6000, TimeUnit.MILLISECONDS)
        .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
