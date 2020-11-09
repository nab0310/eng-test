package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.Map;

/**
 * Represents a Device.
 */
@AutoValue
@JsonDeserialize(builder = Device.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Device {
  @JsonProperty("_links")
  public abstract Map<String, Link> links();

  @JsonProperty("deviceIdentifier")
  public abstract String deviceIdentifier();

  // TODO :What are the valid types of state?
  @JsonProperty("state")
  public abstract String state();

  // TODO: There are a lot of other properties I would add here, but I don't know if they are useful for compound users.

  public static Device.Builder builder() {
    return new AutoValue_Device.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return Device.builder();
    }

    @JsonProperty("_links")
    public abstract Builder links(Map<String, Link> links);

    @JsonProperty("deviceIdentifier")
    public abstract Builder deviceIdentifier(String deviceIdentifier);

    @JsonProperty("state")
    public abstract Builder state(String state);

    public abstract Device build();
  }
}
