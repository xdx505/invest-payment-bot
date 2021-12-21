package ru.xdx505.botapi.mvc.data.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.xdx505.botapi.mvc.constants.OrderState;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Order extends IdentifiableUUID {

  @NonNull
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @NonNull
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Product product;

  @NonNull
  @NotNull
  private Double purchasePrice;

  @CreatedDate
  private ZonedDateTime createdAt;

  @Setter
  @NotNull
  @Enumerated(value = EnumType.STRING)
  private OrderState orderStatus = OrderState.CREATED;
}
