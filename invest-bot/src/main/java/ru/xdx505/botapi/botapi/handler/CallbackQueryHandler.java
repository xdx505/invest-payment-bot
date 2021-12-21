package ru.xdx505.botapi.botapi.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xdx505.botapi.mvc.constants.BotState;

public interface CallbackQueryHandler {
  BotApiMethod<?> handle(Update update);

  BotState getHandlerName();
}
