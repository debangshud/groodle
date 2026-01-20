package org.groodle.boot.service.product.controller;

import org.groodle.boot.service.product.model.Manufacturer;
import org.groodle.boot.service.product.model.Product;
import org.groodle.boot.service.product.service.ManufacturerService;
import org.groodle.boot.service.product.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;
    private final ManufacturerService manufacturerService;
    public ProductController(ProductService service,ManufacturerService manufacturerService) {
        this.service = service;
        this.manufacturerService = manufacturerService;
    }

    @QueryMapping(name = "findAll")
    public List<Product> findAll(@Argument int count){
        return service.findAll(count);
    }

    @QueryMapping(name = "findByID")
    public Product findByID(@Argument Long id){
        return service.findById(id);
    }

    @QueryMapping(name = "count")
    public Long count(){
        return service.count();
    }

    @MutationMapping(name = "createProduct")
    public Product createProduct(@Argument String name, @Argument String description, @Argument Long id) {
        return service.create(name,description,id);
    }

    @MutationMapping(name = "createManufacturer")
    public Manufacturer createManufacturer(@Argument String name) {
        return manufacturerService.create(name);
    }
}
