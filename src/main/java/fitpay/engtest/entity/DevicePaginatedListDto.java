package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Offset based pagination list of {@link Device}.
 *
 * <p>Represents an offset based paginated list in a rest response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@AutoValue
@JsonDeserialize(builder = DevicePaginatedListDto.Builder.class)
public abstract class DevicePaginatedListDto implements OffsetPaginatedListDto<Device> {
  @Override
  @JsonProperty("results")
  public abstract List<Device> results();

  public static Builder builder() {
    return new AutoValue_DevicePaginatedListDto.Builder().results(new ArrayList<>());
  }

  @AutoValue.Builder
  public abstract static class Builder
      implements OffsetPaginatedListDto.Builder<DevicePaginatedListDto.Builder, Device> {
    @JsonCreator
    public static Builder create() {
      return DevicePaginatedListDto.builder();
    }

    @Override
    @JsonProperty("results")
    public abstract Builder results(List<Device> items);

    @Override
    public abstract DevicePaginatedListDto build();
  }
}

