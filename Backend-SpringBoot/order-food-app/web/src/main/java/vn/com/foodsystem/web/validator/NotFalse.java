package vn.com.foodsystem.web.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NotFalseValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotFalse {

    String message() default "NotFalse";

    String[] messages();

    String[] properties();

    String[] verifiers();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

