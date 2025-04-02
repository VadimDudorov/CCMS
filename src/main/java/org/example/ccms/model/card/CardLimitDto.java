package org.example.ccms.model.card;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.math.BigDecimal;

import static org.example.ccms.constants.regex.NAME_LIMIT;

public record CardLimitDto(@NotNull(message = "Параметр id обязателен для заполнения")
                           Long id,
                           @NotNull(message = "Параметр limit обязателен для заполнения")
                           @Min(value = 1, message = "Минимальный лимит должен быть больше 0")
                           BigDecimal limit,
                           @NotBlank(message = "Параметр status обязателен для заполнения")
                           @Pattern(regexp = NAME_LIMIT,
                                   message = "Параметр nameLimit не равен day,month")
                           String nameLimit
) {
}
