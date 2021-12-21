package ru.xdx505.botapi.mvc.data.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.UUID;

@Getter
@ToString
@MappedSuperclass
public abstract class IdentifiableUUID {

  @Id
  @GeneratedValue
  protected UUID id;

  @Version
  protected Long version;

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
