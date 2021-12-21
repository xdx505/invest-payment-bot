package ru.xdx505.botapi.botapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.xdx505.botapi.botapi.handler.CallbackQueryHandler;
import ru.xdx505.botapi.botapi.handler.MessageHandler;
import ru.xdx505.botapi.mvc.constants.BotState;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class BotStateContext {
  private final Map<BotState, CallbackQueryHandler> callbackQueryHandlers;
  private final Map<BotState, MessageHandler> messageHandlers;

  @Autowired
  public BotStateContext(
    List<MessageHandler> messageHandlers,
    List<CallbackQueryHandler> callbackQueryHandlers
  ) {
    this.messageHandlers = messageHandlers.stream().collect(Collectors.toMap(MessageHandler::getHandlerName, Function.identity()));
    this.callbackQueryHandlers = callbackQueryHandlers.stream().collect(Collectors.toMap(CallbackQueryHandler::getHandlerName, Function.identity()));
  }

  public BotApiMethod<?> processCallbackQuery(BotState botState, Update update) {
    return callbackQueryHandlers.get(botState).handle(update);
  }

  public BotApiMethod<?> processInputMessage(BotState botState, Message message) {
    return messageHandlers.get(botState).handle(message);
  }
}
