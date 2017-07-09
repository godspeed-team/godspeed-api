package team.godspeed.api;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ErrorMessageTest {

  @Test
  public void verifyErrorMessageProvidesCodeAndMessageFromError() {
    for (Error error : Error.values()) {
      ErrorMessage sut = new ErrorMessage(error);
      assertThat(sut.getCode(), is(error.getCode()));
      assertThat(sut.getMessage(), is(error.getMessage()));
    }
  }
}
