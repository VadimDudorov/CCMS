package org.example.ccms.model.password;

import javax.validation.constraints.NotBlank;

public record PasswordDto(@NotBlank(message = "Параметр login обязателен для заполнения")
                          String login,
                          @NotBlank(message = "Параметр password обязателен для заполнения")
                          String password,
                          @NotBlank(message = "Параметр password обязателен для заполнения")
                          String roles
) {
}