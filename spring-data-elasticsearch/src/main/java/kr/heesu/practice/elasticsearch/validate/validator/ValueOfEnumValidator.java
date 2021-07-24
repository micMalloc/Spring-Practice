package kr.heesu.practice.elasticsearch.validate.validator;

import kr.heesu.practice.elasticsearch.validate.annotation.EnumValue;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ValueOfEnumValidator implements ConstraintValidator<EnumValue, String> {

    private Set<String> acceptedValues;

    @Override
    public void initialize(EnumValue annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        log.info("acceptedValues = " + acceptedValues.toString());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .map(val -> acceptedValues.contains(val.toLowerCase()))
                .orElse(false);
    }
}
