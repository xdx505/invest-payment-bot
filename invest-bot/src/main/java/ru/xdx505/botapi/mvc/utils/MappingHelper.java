package ru.xdx505.botapi.mvc.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MappingHelper {

  private final ModelMapper mapper;

  /**
   * Мапит объект в Destination
   *
   * @param source          Объект.
   * @param destinationType Тип в который мапим.
   * @param <D>             Тип в который мапим.
   * @return Конечная сущность.
   */
  public <D, S> D map(S source, Class<D> destinationType) {
    Objects.requireNonNull(source);
    Objects.requireNonNull(destinationType);
    return this.mapper.map(source, destinationType);
  }

  /**
   * Мапит коллекцию в Destination
   *
   * @param source          Коллекция объектов.
   * @param destinationType Тип в который мапим.
   * @param <D>             Тип в который мапим.
   * @return Коллекция сущностей.
   */
  public <D, S> List<D> map(Collection<S> source, Class<D> destinationType) {
    Objects.requireNonNull(source);
    Objects.requireNonNull(destinationType);
    return source.stream()
      .map(element -> this.map(element, destinationType))
      .collect(Collectors.toList());
  }

  /**
   * Производит мапинг страницы сущностей.
   *
   * @param page            Страница исходных сущностей.
   * @param pageable        Объект {@link Pageable}.
   * @param destinationType Тип в который мапим.
   * @param <D>             Тип в который мапим.
   * @param <S>             Исходный тип.
   * @return Страница конечных сущностей.
   */
  public <D, S> Page<D> map(Page<S> page, Pageable pageable, Class<D> destinationType) {
    Objects.requireNonNull(page);
    Objects.requireNonNull(pageable);
    List<S> sourceEntities = page.getContent();
    var destinationEntities = this.map(sourceEntities, destinationType);
    return new PageImpl<>(destinationEntities, pageable, page.getTotalElements());
  }

}
