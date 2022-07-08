package org.groodle.boot.service.discount.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.discount.model.Discount;
import org.groodle.boot.service.discount.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DiscountController {

    private DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscount(@PathVariable Long id){
        return new ResponseEntity<>(discountService.getDiscount(id), HttpStatus.OK);
    }
}
