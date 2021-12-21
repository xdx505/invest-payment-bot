package ru.xdx505.botapi.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.xdx505.botapi.mvc.data.dto.ProductDto;
import ru.xdx505.botapi.mvc.service.ProductService;
import ru.xdx505.botapi.mvc.utils.MappingHelper;

import java.util.UUID;

@RestController()
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;
  private final MappingHelper mappingHelper;

  @GetMapping("{uuid}")
  public ProductDto getProduct(
    @PathVariable String uuid
  ) {
    return mappingHelper.map(productService.findByUUID(UUID.fromString(uuid)), ProductDto.class);
  }
}
