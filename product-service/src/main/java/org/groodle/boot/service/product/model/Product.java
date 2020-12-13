package org.groodle.boot.service.product.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false, updatable = false)
    private Manufacturer manufacturer;
}
