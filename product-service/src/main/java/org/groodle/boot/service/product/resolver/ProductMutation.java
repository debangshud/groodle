package org.groodle.boot.service.product.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.groodle.boot.service.product.model.Manufacturer;
import org.groodle.boot.service.product.model.Product;
import org.groodle.boot.service.product.repository.ManufacturerRepository;
import org.groodle.boot.service.product.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMutation implements GraphQLMutationResolver {

    private ManufacturerRepository manufacturerRepository;
    private ProductRepository productRepository;

    public ProductMutation(ManufacturerRepository manufacturerRepository, ProductRepository productRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, String description, Long id) {

        Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);
        if (manufacturer == null) {
            return null;
        }
        Product product = Product.builder()
                .name(name)
                .description(description)
                .manufacturer(Manufacturer.builder().id(id).build())
                .build();
        return productRepository.save(product);
    }
}
