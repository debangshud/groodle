package org.groodle.boot.service.reference.listener;

import lombok.SneakyThrows;
import org.groodle.boot.service.reference.model.Country;
import org.groodle.boot.service.reference.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class ReferenceServiceApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CountryRepository repository;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Country country = new Country();
        country.setName("India");
        country.setAlpha2("IN");
        country.setAlpha3("IND");
        repository.save(country);
    }
}
