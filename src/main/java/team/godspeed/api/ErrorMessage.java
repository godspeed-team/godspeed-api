package team.godspeed.api;

public class ErrorMessage {

  private final Error error;

  public ErrorMessage(Error error) {
    this.error = error;
  }

  public String getCode() {
    return error.getCode();
  }

  public String getMessage() {
    return error.getMessage();
  }
}
