package org.example.ccms.model.card;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static org.example.ccms.constants.regex.STATUS_CARDS;

public record CardStatusDto(@NotNull(message = "Параметр id обязателен для заполнения")
                            Long id,
                            @NotBlank(message = "Параметр status обязателен для заполнения")
                            @Pattern(regexp = STATUS_CARDS,
                                    message = "Параметр status не равен Активна, Заблокирована, Истек срок действия")
                            String status
) {
}
