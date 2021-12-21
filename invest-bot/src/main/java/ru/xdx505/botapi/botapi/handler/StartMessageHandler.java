package ru.xdx505.botapi.botapi.handler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.xdx505.botapi.botapi.entity.message.StartMessage;
import ru.xdx505.botapi.mvc.constants.BotState;

@Component
@AllArgsConstructor
public class StartMessageHandler implements MessageHandler {
  private final StartMessage startMessage;

  @Override
  public BotApiMethod<?> handle(Message message) {
    return startMessage.handle(message.getChatId().toString());
  }

  @Override
  public BotState getHandlerName() {
    return BotState.START;
  }
}
