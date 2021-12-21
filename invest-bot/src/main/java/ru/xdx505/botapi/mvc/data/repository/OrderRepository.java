package ru.xdx505.botapi.mvc.data.repository;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.xdx505.botapi.mvc.data.model.Order;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
  Page<Order> findAll(Pageable pageable);

  List<Order> findAllByUserTelegramId(@NonNull @NotNull Long telegramId);
}
