package org.groodle.boot.service.product.web.rest;

import org.groodle.boot.service.product.model.Product;
import org.groodle.boot.service.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProductReactiveController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    private Flux<Product> getAll() {
        return Flux.fromIterable(repository.findAll());
    }
}
