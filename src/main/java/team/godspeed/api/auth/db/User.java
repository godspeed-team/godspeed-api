package team.godspeed.api.auth.db;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "email_idx", columnNames = {"email"})})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(unique = true)
  @NotNull
  private String email;
  @Column
  @NotNull
  private String passwordEncrypted;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    if (email == null) {
      throw new IllegalArgumentException("email needs to be given");
    }

    this.email = email.toLowerCase();
  }

  public String getPasswordEncrypted() {
    return passwordEncrypted;
  }

  public void setPasswordEncrypted(String passwordEncrypted) {
    this.passwordEncrypted = passwordEncrypted;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final User other = (User) obj;
    return Objects.equals(this.id, other.id) //
        && Objects.equals(this.email, other.email) //
        && Objects.equals(this.passwordEncrypted, other.passwordEncrypted);

  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, passwordEncrypted);
  }

  @Override
  @SuppressWarnings("squid:S2068")
  public String toString() {
    return new ToStringCreator(this) //
        .append("id", id) //
        .append("email", email) //
        .append("password", "********") //
        .toString();
  }

}
