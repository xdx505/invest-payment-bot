package ru.xdx505.botapi.botapi;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
public class Bot extends SpringWebhookBot {
  private final TelegramFacade telegramFacade;
  private String botPath;
  private String botUsername;
  private String botToken;

  public Bot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
    super(setWebhook);
    this.telegramFacade = telegramFacade;
  }

  public Bot(DefaultBotOptions options, SetWebhook setWebhook, TelegramFacade telegramFacade) {
    super(options, setWebhook);
    this.telegramFacade = telegramFacade;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return telegramFacade.handleUpdate(update);
  }
}
