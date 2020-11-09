package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/**
 * Represents a combined UserInfo type.
 */
@AutoValue
@JsonDeserialize(builder = UserInfo.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class UserInfo {
  @JsonProperty("_links")
  @Nullable
  public abstract Map<String, Link> links();

  @JsonProperty("devices")
  public abstract List<Device> devices();

  @JsonProperty("creditCards")
  public abstract List<CreditCard> creditCards();

  @JsonProperty("userId")
  public abstract String userId();

  public static Builder builder() {
    return new AutoValue_UserInfo.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return builder();
    }

    @JsonProperty("_links")
    public abstract Builder links(Map<String, Link> links);

    @JsonProperty("devices")
    public abstract Builder devices(List<Device> devices);

    @JsonProperty("creditCards")
    public abstract Builder creditCards(List<CreditCard> creditCards);

    @JsonProperty("userId")
    public abstract Builder userId(String userId);

    public abstract UserInfo build();
  }
}
