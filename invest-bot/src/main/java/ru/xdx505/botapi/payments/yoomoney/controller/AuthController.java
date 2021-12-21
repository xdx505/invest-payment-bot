package ru.xdx505.botapi.payments.yoomoney.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.xdx505.botapi.payments.yoomoney.service.AuthService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthController {
  public final AuthService authService;

  @GetMapping("yoo")
  public RedirectView getAccessToken(
    @RequestParam String chatId,
    @RequestParam String code
  ) throws Exception {
    authService.getAccessToken(chatId, code);

    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("https://t.me/xdx505_test_bot");
    return redirectView;
  }

  @Profile("dev")
  @GetMapping("init")
  public String authorizeRequest(
    @RequestParam(required = false) Long chatId
  ) throws IOException {
    return authService.authorizeRequest(123321L);
  }
}
