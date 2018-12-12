package org.acacia.sample.springboot.mongodb.controllers;

import org.acacia.sample.springboot.mongodb.domain.Product;
import org.acacia.sample.springboot.mongodb.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * GET /products - get all products
 * GET /products/{id} - get product by id
 * POST /products - create a product
 */

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam BigDecimal price) {
        if(price == null) {
            return repository.findAll();
        }else{
            Product product = new Product();
            product.setPrice(price);
            Example<Product> productExample = Example.of(product);
            return repository.findAll(productExample);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        final Product savedProduct = repository.insert(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/products/{id}")
    public Product getProductbyId(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }
}
