package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Product;
import org.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService service;

  @GetMapping
  @ResponseBody
  public List<Product> getAll() {
    return service.getAll();
  }

  @GetMapping(params = "id")
  @ResponseBody
  public Product getById(@RequestParam long id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseBody
  public Product save(Product product) {
    return service.save(product);
  }

  @DeleteMapping(params = "id")
  @ResponseBody
  public void removeById(@RequestParam long id) {
    service.removeById(id);
  }
}
