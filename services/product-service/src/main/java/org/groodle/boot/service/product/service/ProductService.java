package org.groodle.boot.service.product.service;

import org.groodle.boot.service.product.exception.ProductNotFoundException;
import org.groodle.boot.service.product.model.Manufacturer;
import org.groodle.boot.service.product.model.Product;
import org.groodle.boot.service.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> findAll(int count) {
        return repository.findAll().stream().limit(count).collect(Collectors.toCollection(ArrayList::new));
    }

    public Long count() {
        return repository.count();
    }

    public Product create(String name, String description, Long id) {
        Product product = Product.builder()
                .name(name)
                .description(description)
                .manufacturer(Manufacturer.builder().id(id).build())
                .build();
        return repository.save(product);
    }
}
