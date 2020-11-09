package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Offset based pagination list of {@link User}.
 *
 * <p>Represents an offset based paginated list in a rest response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@AutoValue
@JsonDeserialize(builder = UserPaginatedListDto.Builder.class)
public abstract class UserPaginatedListDto implements OffsetPaginatedListDto<User> {
  @Override
  @JsonProperty("results")
  public abstract List<User> results();

  public static Builder builder() {
    return new AutoValue_UserPaginatedListDto.Builder().results(new ArrayList<>());
  }

  @AutoValue.Builder
  public abstract static class Builder
      implements OffsetPaginatedListDto.Builder<UserPaginatedListDto.Builder, User> {
    @JsonCreator
    public static Builder create() {
      return UserPaginatedListDto.builder();
    }

    @Override
    @JsonProperty("results")
    public abstract Builder results(List<User> items);

    @Override
    public abstract UserPaginatedListDto build();
  }
}
