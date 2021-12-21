package ru.xdx505.botapi.mvc.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class PaginationHelper {
  public static <T> Page<T> pageOf(Collection<T> collection, Pageable pageable) {
    List<T> pageContent = collection.stream()
      .skip((long) pageable.getPageNumber() * pageable.getPageSize())
      .limit(pageable.getPageSize())
      .toList();
    return new PageImpl<>(pageContent, pageable, collection.size());
  }
}
