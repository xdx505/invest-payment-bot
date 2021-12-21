package ru.xdx505.botapi.mvc.data.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.xdx505.botapi.mvc.constants.BotState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User extends IdentifiableUUID {
  @NonNull
  @NotNull
  @Column(nullable = false, updatable = false, unique = true)
  private Long telegramId;

  @NonNull
  @NotNull
  @Column(nullable = false)
  private String username;

  @Setter
  @NotNull
  @Column(nullable = false)
  private boolean isAdmin = false;

  @Setter
  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private BotState state = BotState.START;

  @Setter
  @OneToMany(fetch = FetchType.LAZY)
  private Collection<Order> orders;

  @CreatedDate
  @Column(updatable = false)
  private ZonedDateTime createdAt;

  @LastModifiedDate
  private ZonedDateTime updatedAt;
}
