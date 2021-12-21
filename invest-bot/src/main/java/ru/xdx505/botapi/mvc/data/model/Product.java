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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends IdentifiableUUID {
  @Setter
  @NonNull
  @NotNull
  @Column(nullable = false)
  private String name;

  @Setter
  @NonNull
  private String description;

  @Setter
  @NotNull
  @Column(nullable = false)
  private boolean isActive = true;

  @Setter
  @NonNull
  @NotNull
  @Column(nullable = false)
  private String payedContent;

  @Setter
  @NonNull
  @Min(74)
  @Max(735869)
  @Column(nullable = false)
  private Double price;

  public Double getConvertedTelegramPrice() {
    return BigDecimal.valueOf(this.price).multiply(BigDecimal.valueOf(100)).doubleValue();
  }
}

