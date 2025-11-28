package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class) // Apunta a la lógica
@Target({ ElementType.TYPE }) // Se aplica a la CLASE, no solo a un campo
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {
    String message() default "The end date must be after the start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
