package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.Map;

/**
 * Represents a CreditCard.
 */
@AutoValue
@JsonDeserialize(builder = CreditCard.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CreditCard {
  @JsonProperty("_links")
  public abstract Map<String, Link> links();

  @JsonProperty("creditCardId")
  public abstract String creditCardId();

  @JsonProperty("userId")
  public abstract String userId();

  @JsonProperty("state")
  public abstract String state();

  // TODO: There are a lot of other properties I would add here, but I don't know if they are useful for compound users.

  public static Builder builder() {
    return new AutoValue_CreditCard.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return CreditCard.builder();
    }

    @JsonProperty("_links")
    public abstract Builder links(Map<String, Link> links);

    @JsonProperty("creditCardId")
    public abstract Builder creditCardId(String creditCardId);

    @JsonProperty("userId")
    public abstract Builder userId(String userId);

    @JsonProperty("state")
    public abstract Builder state(String state);

    public abstract CreditCard build();
  }
}
