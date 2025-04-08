package org.example.ccms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.ccms.model.card.CardDto;
import org.example.ccms.model.card.CardLimitDto;
import org.example.ccms.model.card.CardStatusDto;
import org.example.ccms.model.card.CardTransactionResponse;
import org.example.ccms.model.user.UserDto;
import org.example.ccms.service.AdminService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping({"/api/"})
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(
            description = "Метод, используемый для создания карт"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ответ, содержащий id созданной карты"
    )
    @PostMapping("/create-card")
    public Long createCard(
            @Parameter(description = "DTO информации о карте")
            @Validated @RequestBody CardDto cardDto) {
        return adminService.createCard(cardDto);
    }

    @Operation(
            description = "Метод, используемый для обновления лимита"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PatchMapping("/update-limit")
    public void updateCardLimit(
            @Parameter(description = "DTO информации о карте")
            @Validated @RequestBody CardLimitDto cardLimitDto) {
        adminService.updateCardLimit(cardLimitDto);
    }

    @Operation(
            description = "Метод, используемый для обновления статуса"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PatchMapping("/update-status")
    public void updateStatus(
            @Parameter(description = "DTO информации о статусе")
            @Validated @RequestBody CardStatusDto cardStatusDto) {
        adminService.updateCardStatus(cardStatusDto);
    }

    @Operation(
            description = "Метод, используемый для удаления карты"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @DeleteMapping("/delete-card")
    public void deleteCard(
            @Parameter(description = "id сущности карты")
            @NotNull(message = "Отсутствует cardId")
            Long cardId) {
        adminService.deleteCard(cardId);
    }

    @Operation(
            description = "Метод, используемый для создания пользователя"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PostMapping("/create-user")
    public Long createUser(
            @Parameter(description = "DTO информации пользователя")
            @Validated @RequestBody UserDto userDto) {
        return adminService.createUser(userDto);
    }

    @Operation(
            description = "Метод, используемый для удаления пользователя"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @DeleteMapping("/delete-user")
    public void deleteUser(
            @Parameter(description = "Идентификатор пользователя")
            @NotNull(message = "Отсутствует userId")
            Long userId) {
        adminService.deleteUser(userId);
    }

    @Operation(
            description = "Метод, используемый для получения информации по картам и их транзакциям"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @GetMapping("/check-card-admin")
    public List<CardTransactionResponse> getCheckUser(
            @Parameter(description = "Идентификатор пользователя")
            @NotNull(message = "Отсутствует userId")
            Long userId) {
        return adminService.getCardTransaction(userId);
    }
}
