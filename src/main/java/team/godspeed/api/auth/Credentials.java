package team.godspeed.api.auth;

import java.util.Objects;

import org.springframework.core.style.ToStringCreator;

public class Credentials {

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Credentials other = (Credentials) obj;
    return Objects.equals(this.username, other.username) //
        && Objects.equals(this.password, other.password);

  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    return new ToStringCreator(this) //
        .append("username", username) //
        .append("password", "********") //
        .toString();
  }
}
