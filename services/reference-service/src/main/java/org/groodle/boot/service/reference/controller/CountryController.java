package org.groodle.boot.service.reference.controller;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.reference.model.Country;
import org.groodle.boot.service.reference.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Country>> getAll(){
        return ok(service.findAll());
    }

    @GetMapping("/{alpha2}")
    public ResponseEntity<Country> getByAlpha2(@PathVariable String alpha2){
        return ok(service.findByAlpha2(alpha2));
    }
}
