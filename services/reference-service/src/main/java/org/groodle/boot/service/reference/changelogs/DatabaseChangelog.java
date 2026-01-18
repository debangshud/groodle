package org.groodle.boot.service.reference.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.impl.MongockTemplate;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.groodle.boot.service.reference.model.Country;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "001", author = "Debangshu Dasgupta")
    public void insert(MongockTemplate mongockTemplate) throws IOException {

        CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource("iso-country-codes.csv").getInputStream()));

        HeaderColumnNameMappingStrategy<Country> beanStrategy = new HeaderColumnNameMappingStrategy<>();
        beanStrategy.setType(Country.class);

        CsvToBean<Country> csvToBean = new CsvToBean<>();
        csvToBean.setCsvReader(reader);
        csvToBean.setMappingStrategy(beanStrategy);

        List<Country> countries = csvToBean.parse();
        countries.forEach(mongockTemplate::save);
    }
}
