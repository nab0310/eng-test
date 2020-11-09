package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Offset based pagination list of {@link CreditCard}.
 *
 * <p>Represents an offset based paginated list in a rest response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@AutoValue
@JsonDeserialize(builder = CreditCardPaginatedListDto.Builder.class)
public abstract class CreditCardPaginatedListDto implements OffsetPaginatedListDto<CreditCard> {
  @Override
  @JsonProperty("results")
  public abstract List<CreditCard> results();

  public static Builder builder() {
    return new AutoValue_CreditCardPaginatedListDto.Builder().results(new ArrayList<>());
  }

  @AutoValue.Builder
  public abstract static class Builder
      implements OffsetPaginatedListDto.Builder<CreditCardPaginatedListDto.Builder, CreditCard> {
    @JsonCreator
    public static Builder create() {
      return CreditCardPaginatedListDto.builder();
    }

    @Override
    @JsonProperty("results")
    public abstract Builder results(List<CreditCard> items);

    @Override
    public abstract CreditCardPaginatedListDto build();
  }
}
