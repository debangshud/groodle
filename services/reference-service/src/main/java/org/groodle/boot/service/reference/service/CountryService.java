package org.groodle.boot.service.reference.service;

import org.groodle.boot.service.reference.model.Country;
import org.groodle.boot.service.reference.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> findAll() {
        return repository.findAll();
    }

    public Country findByAlpha2(String alpha2) {
        return repository.findByAlpha2(alpha2);
    }
}
