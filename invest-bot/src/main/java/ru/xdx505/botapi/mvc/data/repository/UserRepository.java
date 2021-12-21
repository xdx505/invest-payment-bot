package ru.xdx505.botapi.mvc.data.repository;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.xdx505.botapi.mvc.data.model.User;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  Page<User> findAll(Pageable pageable);

  Optional<User> findByTelegramId(@NonNull @NotNull Long telegramId);

  @Query("""
    from User u
    where u.isAdmin = true
    """)
  Collection<User> findAllAdmins();
}
