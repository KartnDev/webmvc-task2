package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Product;
import org.example.exception.ItemNotFoundException;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository repository;

  public List<Product> getAll() {
    return repository.getAll();
  }

  public Product getById(long id) {
    return repository.getById(id).orElseThrow(ItemNotFoundException::new);
  }

  public Product save(Product product) {
    return repository.save(product).orElseThrow(ItemNotFoundException::new);
  }

  public void removeById(long id) {
    if (!repository.removeById(id)) {
      throw new ItemNotFoundException();
    }
  }
}
