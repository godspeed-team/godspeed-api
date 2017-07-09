package team.godspeed.api.auth;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.godspeed.api.auth.db.User;
import team.godspeed.api.auth.db.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository users;
  @Autowired
  private PasswordEncryptor encryptor;

  public User authenticate(String email, String password) {
    User user = users.findByEmail(email);

    return (user != null && encryptor.checkPassword(password, user.getPasswordEncrypted())) //
        ? user //
        : null;
  }
}
