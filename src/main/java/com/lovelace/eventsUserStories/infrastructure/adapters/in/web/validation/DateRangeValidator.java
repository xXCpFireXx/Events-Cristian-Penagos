package com.lovelace.eventsUserStories.infrastructure.adapters.in.web.validation;

import com.lovelace.eventsUserStories.infrastructure.adapters.in.web.dto.EventRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, EventRequestDTO> {

    @Override
    public boolean isValid(EventRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            return true; // Dejamos que @NotNull maneje los nulos
        }
        // Retorna TRUE si la fecha fin es DESPUÉS de la fecha inicio
        return dto.getEndDate().isAfter(dto.getStartDate());
    }
}