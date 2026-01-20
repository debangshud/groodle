package org.groodle.boot.service.product.service;

import org.groodle.boot.service.product.model.Manufacturer;
import org.groodle.boot.service.product.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {
    private final ManufacturerRepository repository;
    public ManufacturerService(ManufacturerRepository repository) {
        this.repository = repository;
    }
    public Manufacturer create(String name) {
        Manufacturer manufacturer = Manufacturer.builder().name(name).build();
        return repository.save(manufacturer);
    }
}
