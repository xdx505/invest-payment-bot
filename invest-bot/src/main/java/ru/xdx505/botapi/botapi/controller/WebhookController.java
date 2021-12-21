package ru.xdx505.botapi.botapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.xdx505.botapi.botapi.Bot;

@RestController
@RequiredArgsConstructor
public class WebhookController {
  private final Bot telegramBot;

  @PostMapping("/bot")
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    return telegramBot.onWebhookUpdateReceived(update);
  }

  @Profile("dev")
  @PostMapping("/dropPendingUpdates")
  public ResponseEntity<Integer> dropPendingUpdates() throws TelegramApiException {
    var setWebhook = telegramBot.getSetWebhook();
    setWebhook.setDropPendingUpdates(true);
    telegramBot.setWebhook(setWebhook);
    return new ResponseEntity<>(telegramBot.getWebhookInfo().getPendingUpdatesCount(), HttpStatus.OK);
  }
}
