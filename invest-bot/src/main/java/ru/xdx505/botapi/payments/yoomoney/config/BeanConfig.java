package ru.xdx505.botapi.payments.yoomoney.config;

import com.yoo.money.api.authorization.AuthorizationData;
import com.yoo.money.api.authorization.AuthorizationParameters;
import com.yoo.money.api.net.clients.ApiClient;
import com.yoo.money.api.net.clients.DefaultApiClient;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
  private final YooConfig yooConfig;

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder()
      .build();
  }

  @Profile("!dev")
  @Bean
  public ApiClient client() {
    return new DefaultApiClient.Builder()
      .setClientId(yooConfig.getClientId())
      .create();
  }

  @Profile("dev")
  @Bean
  public ApiClient clientDev() {
    var client = new DefaultApiClient.Builder()
      .setClientId(yooConfig.getClientId())
      .create();
    client.setAccessToken(yooConfig.getAuthToken());
    return client;
  }
}
