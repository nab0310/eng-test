package fitpay.engtest.error;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
  private HttpStatus status;
  private String message;

  public ServiceException(HttpStatus status) {
    this.status = status;
    this.message = status.getReasonPhrase();
  }

  public ServiceException(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return this.status;
  }
  public String getMessage() { return this.message; }
}
