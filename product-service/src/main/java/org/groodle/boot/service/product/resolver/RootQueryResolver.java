package org.groodle.boot.service.product.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.groodle.boot.service.product.model.Product;
import org.groodle.boot.service.product.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class RootQueryResolver implements GraphQLQueryResolver {

    private final ProductRepository productRepository;

    public RootQueryResolver(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getProducts(final int count){
        return productRepository.findAll().stream().limit(count).collect(Collectors.toCollection(ArrayList::new));
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Long getProductCount(){
        return productRepository.count();
    }
}
