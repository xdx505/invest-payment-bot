package ru.xdx505.botapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.xdx505.botapi.botapi.service.TelegramApiService;
import ru.xdx505.botapi.mvc.data.model.Product;

import javax.persistence.EntityManager;

@Slf4j
@Component
@Profile("dev")
@Transactional
@RequiredArgsConstructor
public class ApplicationRunnerDev implements ApplicationRunner {
  private final TelegramApiService telegramApiService;
  private final EntityManager entityManager;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    telegramApiService.registerAndDropPendingUpdates();
    generateProducts(23);
  }

  private void generateProducts(int count) {
    for (int i = 1; i <= count; i++) {
      entityManager.persist(new Product(
        "Тестовый товар " + i,
        "Описание тестового товара",
        "Платный контент",
        (80.0 + i)
      ));
    }
  }
}
