package org.groodle.boot.service.product.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.groodle.boot.service.product.model.Product;
import org.groodle.boot.service.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ProductQuery implements GraphQLQueryResolver {
    @Autowired
    private ProductRepository repository;

    public Iterable<Product> getProducts(final int count){
        return repository.findAll().stream().limit(count).collect(Collectors.toCollection(ArrayList::new));
    }
    public Product getProductById(Long id){
        return repository.findById(id).orElse(null);
    }
}
