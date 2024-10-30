package vn.daianh.spring_basic.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {DobValidator.class}      // class chiu trach nhiem cho validtion nay
)
public @interface DobConstraint {

    String message() default "Invalid date of birth";

    int min();

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
