package kr.heesu.practice.r2dbc.repository.mapper;

import io.r2dbc.spi.Row;
import kr.heesu.practice.r2dbc.entity.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class PersonReadingConverter implements Converter<Row, Person> {

    @Override
    public Person convert(Row source) {
        return Person.builder()
                .id(source.get("p_id", Long.class))
                .name(source.get("p_name", String.class))
                .address(Person.Address.builder()
                        .city(source.get("city", String.class))
                        .street(source.get("street", String.class))
                        .zipCode(source.get("zip_code", String.class))
                        .build())
                .build();
    }
}
