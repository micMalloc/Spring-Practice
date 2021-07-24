package kr.heesu.practice.elasticsearch.validate.annotation;

import kr.heesu.practice.elasticsearch.validate.validator.AccountSearchableFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = AccountSearchableFieldValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Searchable {

    String message() default "Not Searchable Field";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
