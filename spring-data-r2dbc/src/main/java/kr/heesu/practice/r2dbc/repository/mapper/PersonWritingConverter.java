package kr.heesu.practice.r2dbc.repository.mapper;

import kr.heesu.practice.r2dbc.entity.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

@WritingConverter
public class PersonWritingConverter implements Converter<Person, OutboundRow> {

    @Override
    public OutboundRow convert(Person source) {
        OutboundRow row = new OutboundRow();
        if(source.getId() != null) {
            row.put("p_id", Parameter.from(source.getId()));
        }
        row.put("city", Parameter.from(source.getAddress().getCity()));
        row.put("zip_code", Parameter.from(source.getAddress().getZipCode()));
        row.put("street", Parameter.from(source.getAddress().getStreet()));
        row.put("p_name", Parameter.from(source.getName()));
        return row;
    }
}
