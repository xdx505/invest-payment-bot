package ru.xdx505.botapi.payments.yoomoney.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "yoo")
public class YooConfig {
  private String clientId;
  private String clientSecret;
  private String redirectUri;
  private String authToken;
}
