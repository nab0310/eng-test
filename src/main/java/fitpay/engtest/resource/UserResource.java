package fitpay.engtest.resource;

import fitpay.engtest.entity.CreditCard;
import fitpay.engtest.entity.Device;
import fitpay.engtest.entity.OffsetPaginatedListDto;
import fitpay.engtest.entity.User;
import java.util.List;

public interface UserResource {
  /**
   * Retrieve a single user.
   *
   * @param id unique identifier representing the user to retrieve.
   * @return {@link User} representing the requested resource.
   */
  User getUser(String id);

  /**
   * Retrieves all the devices for a given user.
   *
   * @param user {@link User} to retrieve the devices for.
   * @return {@link List<Device>} representing the list of all devices for a {@link User}.
   */
  List<Device> getAllDevices(User user);

  /**
   * Retrieves a paginated list of devices for a given user.
   *
   * @param user {@link User} to retrieve the devices for.
   * @return instance of {@link OffsetPaginatedListDto}
   */
  OffsetPaginatedListDto<Device> getDevices(User user);

  /**
   * Retrieves all the credit cards for a given user.
   *
   * @param user {@link User} to retrieve the devices for.
   * @return {@link List<CreditCard>} representing the list of all credit cards for a {@link User}.
   */
  List<CreditCard> getAllCreditCards(User user);

  /**
   * Retrieves a paginated list of credit cards for a given user.
   *
   * @param user {@link User} to retrieve the credit cards for.
   * @return instance of {@link OffsetPaginatedListDto}
   */
  OffsetPaginatedListDto<CreditCard> getCreditCards(User user);
}
