package ru.xdx505.botapi.botapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import ru.xdx505.botapi.botapi.Bot;
import ru.xdx505.botapi.botapi.TelegramFacade;

@Configuration
@RequiredArgsConstructor
public class ApiConfig {
  private final BotConfig botConfig;

  @Bean
  public SetWebhook setWebhookInstance() {
    return SetWebhook.builder()
      .url((botConfig.getBotPath()))
      .build();
  }

  @Bean
  public DefaultBotOptions defaultBotOptions() {
    var options = new DefaultBotOptions();
    if (botConfig.getProxyType().equals(DefaultBotOptions.ProxyType.NO_PROXY)) return options;
    options.setProxyType(botConfig.getProxyType());
    options.setProxyHost(botConfig.getProxyHost());
    options.setProxyPort(botConfig.getProxyPort());
    return options;
  }

  @Bean
  public Bot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {
    var bot = new Bot(setWebhook, telegramFacade);
    bot.setBotToken(botConfig.getBotToken());
    bot.setBotUsername(botConfig.getBotUsername());
    bot.setBotPath(botConfig.getBotPath());
    return bot;
  }

  @Bean
  public DefaultAbsSender defaultAbsSender() {
    return new DefaultAbsSender(defaultBotOptions()) {
      @Override
      public String getBotToken() {
        return botConfig.getBotToken();
      }
    };
  }

  @Bean
  public SendMessage sendMessage() {
    var message = new SendMessage();
    message.enableHtml(true);
    message.setDisableWebPagePreview(true);
    return message;
  }

  @Bean
  public EditMessageText editMessageText() {
    var editMessageText = new EditMessageText();
    editMessageText.enableHtml(true);
    editMessageText.setDisableWebPagePreview(true);
    return editMessageText;
  }
}
