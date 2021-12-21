package ru.xdx505.botapi.mvc.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.xdx505.botapi.mvc.data.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
  Page<Product> findAll(Pageable pageable);
}
