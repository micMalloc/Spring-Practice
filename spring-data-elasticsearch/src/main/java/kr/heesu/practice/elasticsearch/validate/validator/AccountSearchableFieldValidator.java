package kr.heesu.practice.elasticsearch.validate.validator;

import kr.heesu.practice.elasticsearch.validate.annotation.Searchable;
import kr.heesu.practice.elasticsearch.validate.enums.AccountSearchableFields;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountSearchableFieldValidator implements ConstraintValidator<Searchable, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return AccountSearchableFields.isSearchableField(value);
    }
}
