package ru.xdx505.botapi.mvc.data.dto;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class ProductDto {
  String name;
  String description;
  boolean isActive;
  String payedContent;
  Double price;
  ZonedDateTime createdAt;
  ZonedDateTime updatedAt;
  Double convertedTelegramPrice;
}
