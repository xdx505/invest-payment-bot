package ru.xdx505.botapi.payments.yoomoney.service;

import com.yoo.money.api.authorization.AuthorizationData;
import com.yoo.money.api.authorization.AuthorizationParameters;
import com.yoo.money.api.methods.Token;
import com.yoo.money.api.net.clients.ApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.xdx505.botapi.payments.yoomoney.config.YooConfig;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
  private final ApiClient client;
  private final YooConfig yooConfig;
  private final OkHttpClient okHttpClient;

  public String authorizeRequest(@Nullable Long chatId) throws IOException {
    var authorizationParameters = createAuthorizationParameters(chatId);
    var authorizationData = client.createAuthorizationData(authorizationParameters);
    var request = buildRequest(authorizationData);
    var response = okHttpClient.newCall(request).execute();
    return response.request().url().toString();
  }

  public void getAccessToken(String chatId, String code) throws Exception {
    var token = client.execute(
      new Token.Request(code, client.getClientId(), yooConfig.getRedirectUri(), yooConfig.getClientSecret())
    );
    if (token.error == null) {
      client.setAccessToken(token.accessToken);
      log.info("Токен доступа получен и присвоен");
      log.info("chat id " + chatId);
    } else log.error(token.error.getCode());
  }

  private AuthorizationParameters createAuthorizationParameters(@Nullable Long chatId) {
    return new AuthorizationParameters.Builder()
      .setRedirectUri(chatId == null
        ? yooConfig.getRedirectUri()
        : String.format("%s?chatId=%d", yooConfig.getRedirectUri(), chatId)
      )
      .setResponseType("code")
      .setRawScope("account-info operation-history operation-details incoming-transfers")
      .create();
  }

  private Request buildRequest(AuthorizationData authorizationData) {
    return new Request.Builder()
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Content-Length", String.valueOf(authorizationData.getParameters().length))
      .url(authorizationData.getUrl())
      .post(RequestBody.create(null, authorizationData.getParameters()))
      .build();
  }
}
