package ru.xdx505.botapi.botapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramFacade {

  public SendMessage handleUpdate(Update update) {
    return new SendMessage(update.getMessage().getChatId().toString(), "Всё работает");
  }
}
