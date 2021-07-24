package kr.heesu.practice.elasticsearch.validate.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AccountSearchableFields {

    ACCOUNT_NUMBER("account_number"),
    FIRSTNAME("firstname"),
    LASTNAME("lastname"),
    STATE("state")
    ;

    private final String field;

    private boolean validate(String field) {
        return this.field.equals(field);
    }

    public static boolean isSearchableField(String field) {
        return Arrays.stream(values())
                .anyMatch(searchableField -> searchableField.validate(field));
    }
}
