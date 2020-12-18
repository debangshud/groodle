package org.groodle.boot.service.reference.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.reference.model.Country;
import org.groodle.boot.service.reference.repository.CountryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/countries")
    public List<Country> getAll(){

        List<Country> all = countryRepository.findAll();

        log.info("Returned Records: {}",all.size());

        return all;
    }

    @GetMapping("/countries/{alpha2}")
    public Country getByAlpha2(@PathVariable String alpha2){
        return countryRepository.findByAlpha2(alpha2);
    }

}
