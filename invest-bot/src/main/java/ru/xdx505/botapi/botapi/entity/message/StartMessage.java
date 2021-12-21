package ru.xdx505.botapi.botapi.entity.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.xdx505.botapi.botapi.entity.button.CatalogButton;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class StartMessage {
  private final SendMessage sendMessage;
  private final CatalogButton catalogButton;


  public SendMessage handle(String chatId) {
    sendMessage.setChatId(chatId);
    sendMessage.setText(getText());
    sendMessage.setReplyMarkup(setInlineKeyboardMarkup());
    return sendMessage;
  }

  private String getText() {
    return """
      Приветствую в разделе <b>ИнвестТема</b>, посвященном вебинарам!

      <i>❗️Обучающие вебинары проводятся еженедельно для <a href="https://t.me/particular_trader/1219">Premium</a> подписчиков, а в этом боте вы сможете оплатить и получить записи некоторых из них.</i>""";
  }

  private InlineKeyboardMarkup setInlineKeyboardMarkup() {
    return InlineKeyboardMarkup.builder()
      .keyboardRow(Collections.singletonList(catalogButton))
      .build();
  }
}
