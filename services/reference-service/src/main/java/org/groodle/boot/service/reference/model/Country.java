package org.groodle.boot.service.reference.model;

import com.opencsv.bean.CsvBindByName;
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
    @CsvBindByName(column = "alpha-2")
    private String alpha2;

    @CsvBindByName(column = "alpha-3")
    @Field("alpha-3")
    private String alpha3;

    @CsvBindByName(column = "name")
    private String name;
}
