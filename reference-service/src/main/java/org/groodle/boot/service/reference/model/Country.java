package org.groodle.boot.service.reference.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "country")
public class Country {

    @MongoId
    private String id;
    @Field("alpha-2")
    private String alpha2;
    @Field("alpha-3")
    private String alpha3;
    private String name;
}
