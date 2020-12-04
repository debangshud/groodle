package org.groodle.boot.service.reference.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.impl.MongockTemplate;
import org.groodle.boot.service.reference.model.Country;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "001", author = "Debangshu Dasgupta")
    public void insert(MongockTemplate mongockTemplate){
        Country country = new Country();
        country.setName("India");
        country.setAlpha2("IN");
        country.setAlpha3("IND");
        mongockTemplate.save(country);
    }
}
