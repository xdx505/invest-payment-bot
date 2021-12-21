package ru.xdx505.botapi.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xdx505.botapi.mvc.data.model.Product;
import ru.xdx505.botapi.mvc.data.repository.ProductRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public Collection<Product> saveAll(Collection<Product> products) {
    return productRepository.saveAll(products);
  }

  public Optional<Product> findByUUID(UUID uuid) {
    return productRepository.findById(uuid);
  }

  public Collection<Product> getAll() {
    return productRepository.findAll();
  }

  public Page<Product> getAllPageable(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  public Page<Product> getAllFixedSizePage(int pageNumber, int pageSize) {
    return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
  }

  public void delete(Product product) {
    productRepository.delete(product);
  }

  public void deleteByUUID(UUID uuid) {
    productRepository.deleteById(uuid);
  }

  public void deleteAll() {
    productRepository.deleteAll();
  }

  public long count() {
    return productRepository.count();
  }
}
