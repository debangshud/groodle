package org.dasgupta.sample.springboot.jpa.h2.repository.product;

import org.dasgupta.sample.springboot.jpa.h2.entity.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
}
