package fitpay.engtest.contoller;

import fitpay.engtest.entity.CreditCard;
import fitpay.engtest.entity.Device;
import fitpay.engtest.entity.Link;
import fitpay.engtest.entity.OffsetPaginatedListDto;
import fitpay.engtest.entity.User;
import fitpay.engtest.entity.UserInfo;
import fitpay.engtest.error.ServiceException;
import fitpay.engtest.resource.UserResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/compositeUsers")
public class CompositeUserController {
  private final UserResource userResource;

  @Autowired
  CompositeUserController(UserResource userResource) {
    this.userResource = userResource;
  }

  @GetMapping("/{userId}")
  public UserInfo getUser(@PathVariable String userId,
      @RequestParam(required = false) String creditCardState,
      @RequestParam(required = false) String deviceState) {
    try {
      User user = userResource.getUser(userId);

      List<CreditCard> creditCards = userResource.getAllCreditCards(user);

      // TODO: Would love to do this filtering in the resource, but it doesn't look like the api
      //  supports it.
      if (creditCardState != null) {
        creditCards = creditCards.stream().filter((CreditCard c) -> c.state().equals(creditCardState)).collect(
            Collectors.toList());
      }

      List<Device> devices = userResource.getAllDevices(user);

      if (deviceState != null) {
        devices = devices.stream().filter((Device device) -> device.state().equals(deviceState)).collect(
            Collectors.toList());
      }

      Map<String, Link> linkMap = new HashMap<>();
      linkMap.put("devices", user.links().get("devices"));
      linkMap.put("creditCards", user.links().get("creditCards"));
      linkMap.put("user", user.links().get("self"));

      return UserInfo.builder()
          .links(linkMap)
          .userId(userId)
          .devices(devices)
          .creditCards(creditCards).build();
    } catch (ServiceException exception) {
      // TODO: Don't love to do this, if I had more time I would figure out a better way to map
      //  the ServiceExceptions to JSON responses.
      throw new ResponseStatusException(exception.getStatus(), exception.getMessage(), exception);
    }
  }

}
