package org.example.ccms.model.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record UserDto(@NotNull(message = "Параметр userId обязателен для заполнения")
                      Long userId,
                      @NotBlank(message = "Параметр firstName обязателен для заполнения")
                      String firstName,
                      @NotBlank(message = "Параметр lastName обязателен для заполнения")
                      String lastName,
                      @NotBlank(message = "Параметр email обязателен для заполнения")
                      String email,
                      @NotNull(message = "Параметр birthDate обязателен для заполнения")
                      LocalDate birthDate) {
}
