package ru.xdx505.botapi.botapi.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.xdx505.botapi.mvc.constants.BotState;

public interface MessageHandler {
  BotApiMethod<?> handle(Message message);

  BotState getHandlerName();
}
