package org.acacia.sample.springboot.mongodb.repositories;

import org.acacia.sample.springboot.mongodb.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
