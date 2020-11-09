package fitpay.engtest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Offset based pagination list.
 */
public interface OffsetPaginatedListDto<T> {

  @JsonProperty("_links")
  public abstract Map<String, Link> links();

  @JsonProperty("results")
  public abstract List<T> results();

  @JsonProperty("limit")
  public abstract Integer limit();

  @JsonProperty("offset")
  public abstract Integer offset();

  @JsonProperty("totalResults")
  public abstract Long totalResults();

  interface Builder<B extends Builder<B, T>, T> {
    @JsonProperty("_links")
    B links(Map<String, Link> links);

    @JsonProperty("results")
    B results(List<T> results);

    @JsonProperty("limit")
    B limit(Integer limit);

    @JsonProperty("offset")
    B offset(Integer offset);

    @JsonProperty("totalResults")
    B totalResults(Long totalResults);

    OffsetPaginatedListDto<T> build();
  }
}
