package ru.xdx505.botapi.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.xdx505.botapi.mvc.data.model.Order;
import ru.xdx505.botapi.mvc.data.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  public Order save(Order Order) {
    return orderRepository.save(Order);
  }

  public Collection<Order> saveAll(Collection<Order> Orders) {
    return orderRepository.saveAll(Orders);
  }

  public Optional<Order> findByUUID(UUID uuid) {
    return orderRepository.findById(uuid);
  }

  public Collection<Order> getAll() {
    return orderRepository.findAll();
  }

  public List<Order> findAllByUserTelegramId(Long telegramId) {
    return orderRepository.findAllByUserTelegramId(telegramId);
  }

  public Page<Order> getAllPageable(Pageable pageable) {
    return orderRepository.findAll(pageable);
  }

  public Page<Order> getAllFixedSizePage(int pageNumber, int pageSize) {
    return orderRepository.findAll(PageRequest.of(pageNumber, pageSize));
  }

  public void delete(Order Order) {
    orderRepository.delete(Order);
  }

  public void deleteByUUID(UUID uuid) {
    orderRepository.deleteById(uuid);
  }

  public void deleteAll() {
    orderRepository.deleteAll();
  }

  public long count() {
    return orderRepository.count();
  }
}
