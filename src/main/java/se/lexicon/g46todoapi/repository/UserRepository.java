package se.lexicon.g46todoapi.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g46todoapi.domain.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Boolean existsByEmail(String email);
  Optional<User> findByEmail(String email);

  @Modifying
  @Transactional
  @Query("update User u set u.expired = :status where u.email = :email")
  void updateExpiredByEmail(@Param("email") String email, @Param("status") boolean status);

  @Modifying
  @Transactional
  @Query("update User u set u.password = :password where u.email = :email")
  void updatePasswordByEmail(@Param("email") String email, @Param("password") String newPassword);

}
