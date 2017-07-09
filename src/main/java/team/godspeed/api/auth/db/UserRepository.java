package team.godspeed.api.auth.db;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

  public User findByEmail(String email);
}
