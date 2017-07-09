package team.godspeed.api.auth.db;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class UserTest {

  @Test(expected = IllegalArgumentException.class)
  public void verifyEmailCannotBeSetToNull() {
    new User().setEmail(null);
  }

  @Test
  public void verifyEmailAddressIsConvertedToLowerCharacters() {
    User user = new User();
    user.setEmail("EmAiL@WiTh.CaPiTaLs");
    assertThat(user.getEmail(), is("email@with.capitals"));
  }
}
