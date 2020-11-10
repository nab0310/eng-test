package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Represents a Link.
 */
@AutoValue
@JsonDeserialize(builder = Link.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Link {
  @JsonProperty("href")
  public abstract String href();

  @JsonProperty(value = "templated", defaultValue = "false")
  public abstract Boolean templated();

  public static Builder builder() {
    return new AutoValue_Link.Builder().templated(false);
  }

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return Link.builder();
    }

    @JsonProperty("href")
    public abstract Builder href(String href);

    @JsonProperty("templated")
    public abstract Builder templated(Boolean templated);

    public abstract Link build();
  }
}
