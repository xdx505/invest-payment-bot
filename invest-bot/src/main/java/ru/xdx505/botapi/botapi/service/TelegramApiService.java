package ru.xdx505.botapi.botapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.xdx505.botapi.botapi.Bot;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramApiService {
  private final Bot bot;

  public void registerAndDropPendingUpdates() throws TelegramApiException {
    var pendingUpdatesCount = bot.getWebhookInfo().getPendingUpdatesCount();
    var webhook = bot.getSetWebhook();
    webhook.setDropPendingUpdates(true);
    bot.execute(webhook);
    log.info(String.format("Ожидающие обновления были отброшены - %d", pendingUpdatesCount));
  }
}
