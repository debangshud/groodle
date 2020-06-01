package org.groodle.boot.service.reference.repository;

import org.groodle.boot.service.reference.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends MongoRepository<Country,String> {
    Country findByAlpha2(String alpha2);
}
