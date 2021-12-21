package ru.xdx505.botapi.botapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xdx505.botapi.mvc.constants.BotState;
import ru.xdx505.botapi.mvc.service.UserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramFacade {
  private final BotStateContext botStateContext;
  private final UserService userService;

  public BotApiMethod<?> handleUpdate(Update update) {
    if (update.hasCallbackQuery()) {
      var callback = update.getCallbackQuery();
      var fromUser = callback.getFrom();
      userService.saveIfNewUser(fromUser.getId(), fromUser.getUserName());
      log.info(
        "New callback from User: {}, userId: {}, with data: {}", fromUser.getUserName(),
        callback.getMessage().getChatId(), callback.getData()
      );
    }
    if (update.hasMessage()) {
      var message = update.getMessage();
      var fromUser = message.getFrom();
      userService.saveIfNewUser(fromUser.getId(), fromUser.getUserName());
      log.info(
        "New message from User: {}, chatId: {}, with text: {}", message.getFrom().getUserName(),
        message.getChatId(), message.getText() == null ? "" : message.getText()
      );
      return processMessage(message);
    }
    return null;
  }

  private BotApiMethod<?> processMessage(Message message) {
    var telegramId = message.getFrom().getId();
    BotState botState = userService.findByTelegramId(telegramId).getState();
    if (message.hasText()) {
      botState = switch (message.getText()) {
        case "/start" -> BotState.START;
        default -> userService.findByTelegramId(telegramId).getState();
      };
    }
    userService.updateState(telegramId, botState);
    return botStateContext.processInputMessage(botState, message);
  }
}
