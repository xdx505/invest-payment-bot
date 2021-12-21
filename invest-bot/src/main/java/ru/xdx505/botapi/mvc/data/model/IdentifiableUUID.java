package ru.xdx505.botapi.mvc.data.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class IdentifiableUUID {

  @Id
  @GeneratedValue
  protected UUID id;

  @Version
  protected Long version;

  @CreatedDate
  @Column(name = "created_at", nullable = false,updatable = false)
  private ZonedDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (id == null) {
      return super.equals(obj);
    }
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!getClass().isAssignableFrom(obj.getClass())) {
      return false;
    }
    IdentifiableUUID other = (IdentifiableUUID) obj;
    return id.equals(other.getId());
  }
}
