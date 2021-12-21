package ru.xdx505.botapi.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.xdx505.botapi.mvc.data.model.User;
import ru.xdx505.botapi.mvc.data.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public Collection<User> saveAll(Collection<User> Users) {
    return userRepository.saveAll(Users);
  }

  public User findByUUID(UUID uuid) {
    return userRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
  }

  public User findByTelegramId(Long telegramId) {
    return userRepository.findByTelegramId(telegramId).orElseThrow(EntityNotFoundException::new);
  }

  public Collection<User> getAll() {
    return userRepository.findAll();
  }

  public Page<User> getAllPageable(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  public Page<User> getAllFixedSizePage(int pageNumber, int pageSize) {
    return userRepository.findAll(PageRequest.of(pageNumber, pageSize));
  }

  public void delete(User User) {
    userRepository.delete(User);
  }

  public void deleteByUUID(UUID uuid) {
    userRepository.deleteById(uuid);
  }

  public void deleteAll() {
    userRepository.deleteAll();
  }

  public long count() {
    return userRepository.count();
  }

  public boolean isAdmin(Long telegramId) {
    return findByTelegramId(telegramId).isAdmin();
  }

  public Collection<User> getAllAdmins() {
    return userRepository.findAllAdmins();
  }
}

