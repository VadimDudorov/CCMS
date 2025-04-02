package org.example.ccms.model.transaction;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

import static org.example.ccms.constants.regex.STATUS_OPERATIONS;

public record TransactionDto(@NotNull(message = "Параметр cardId обязателен для заполнения")
                             Long cardId,
                             @NotBlank(message = "Параметр number обязателен для заполнения")
                             String number,
                             @NotBlank(message = "Параметр operation обязателен для заполнения")
                             @Pattern(regexp = STATUS_OPERATIONS,
                                     message = "Параметр operation не соответствует паттерну")
                             String operation,
                             @NotNull(message = "Параметр amount обязателен для заполнения")
                             @Min(value = 1,
                                     message = "Значение не может быть отрицательным или равным 0")
                             BigDecimal amount
) {
}