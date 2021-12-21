package ru.xdx505.botapi.botapi.entity.button;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.annotation.PostConstruct;

@Getter
@Setter
@Component
@PropertySource(value = "classpath:tgbutton.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "catalog")
public class CatalogButton extends InlineKeyboardButton {
  private String text;
  private String callback;

  @PostConstruct
  public void setTextAndCallback() {
    super.setText(text);
    super.setCallbackData(callback);
  }
}
