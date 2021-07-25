package kr.heesu.practice.r2dbc.config;

import io.r2dbc.spi.ConnectionFactory;
import kr.heesu.practice.r2dbc.repository.mapper.OrderConverter;
import kr.heesu.practice.r2dbc.repository.mapper.PersonReadingConverter;
import kr.heesu.practice.r2dbc.repository.mapper.PersonWritingConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfiguration extends AbstractR2dbcConfiguration {

    @Override
    public ConnectionFactory connectionFactory() {
        return null;
    }

    @Override
    public R2dbcCustomConversions r2dbcCustomConversions() {
        List<Object> converters = new ArrayList<>();
        converters.add(new OrderConverter());
        converters.add(new PersonReadingConverter());
        converters.add(new PersonWritingConverter());

        return new R2dbcCustomConversions(converters);
    }

}
