package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/**
 * Represents a User.
 */
@AutoValue
@JsonDeserialize(builder = User.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class User {
  @JsonProperty("_links")
  public abstract Map<String, Link> links();

  @JsonProperty("id")
  public abstract String id();

  @JsonProperty("createdTs")
  public abstract String createdTs();

  @JsonProperty("createdTsEpoch")
  public abstract Long createdTsEpoch();

  @JsonProperty("lastModifiedTs")
  public abstract String lastModifiedTs();

  @JsonProperty("lastModifiedTsEpoch")
  public abstract Long lastModifiedTsEpoch();

  @JsonProperty("userId")
  public abstract String userId();

  public static Builder builder() {
    return new AutoValue_User.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonCreator
    private static Builder create() {
      return User.builder();
    }

    @JsonProperty("_links")
    public abstract Builder links(Map<String, Link> links);

    @JsonProperty("id")
    public abstract Builder id(String id);

    @JsonProperty("createdTs")
    public abstract Builder createdTs(String createdTs);

    @JsonProperty("createdTsEpoch")
    public abstract Builder createdTsEpoch(Long createdTsEpoch);

    @JsonProperty("lastModifiedTs")
    public abstract Builder lastModifiedTs(String lastModifiedTs);

    @JsonProperty("lastModifiedTsEpoch")
    public abstract Builder lastModifiedTsEpoch(Long lastModifiedTsEpoch);

    @JsonProperty("userId")
    public abstract Builder userId(String userId);

    public abstract User build();
  }
}
