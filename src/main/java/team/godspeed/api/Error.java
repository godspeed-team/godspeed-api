package team.godspeed.api;

public enum Error {
  INVALID_CREDENTIALS("auth-cred", "Invalid credentials or account does not exist");

  private final String message;
  private final String code;

  private Error(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

}
