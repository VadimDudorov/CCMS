package org.example.ccms.model.card;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.example.ccms.constants.regex.STATUS_CARDS;

public record CardDto(@NotNull(message = "Параметр userId обязателен для заполнения")
                      Long userId,
                      @NotBlank(message = "Параметр number обязателен для заполнения")
                      String number,
                      @NotNull(message = "Параметр owner обязателен для заполнения")
                      String owner,
                      @NotNull(message = "Параметр expirationDate обязателен для заполнения")
                      LocalDate expirationDate,
                      @NotBlank(message = "Параметр status обязателен для заполнения")
                      @Pattern(regexp = STATUS_CARDS,
                              message = "Параметр status не равен Активна, Заблокирована, Истек срок действия")
                      String status,
                      @NotNull(message = "Параметр balance обязателен для заполнения")
                      BigDecimal balance
) {
}
