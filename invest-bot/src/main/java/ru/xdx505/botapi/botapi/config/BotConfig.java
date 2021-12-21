package ru.xdx505.botapi.botapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Setter
@Getter
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "bot")
public class BotConfig {
  private String botPath;
  private String botUsername;
  private String botToken;
  private DefaultBotOptions.ProxyType proxyType;
  private String proxyHost;
  private int proxyPort;
}
