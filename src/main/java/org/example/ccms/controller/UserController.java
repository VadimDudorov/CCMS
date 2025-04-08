package org.example.ccms.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.ccms.model.card.CardDto;
import org.example.ccms.model.card.CardTransactionResponse;
import org.example.ccms.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Validated
@RestController
@RequestMapping({"/api/"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            description = "Метод, используемый для просмотра карт"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ответ, содержащий информацию по карте"
    )
    @GetMapping("/check-card-user")
    public List<CardTransactionResponse> createCard(
            @Parameter(description = "id пользователя")
            @NotNull Long userId) {
        return userService.getCardTransaction(userId);
    }

    @Operation(
            description = "Метод, используемый для блокировки карты пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ответ, содержащий об успешности блокировки карты"
    )
    @PatchMapping("/blocked-card-user")
    public Boolean patchCardBlockedController(
            @Parameter(description = "id пользователя")
            @NotNull(message = "Не заполнен userId")
            Long userId) {
        return userService.patchCardBlocked(userId);
    }

    @Operation(
            description = "Метод, используемый для перевода между двумя картами пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ответ, содержащий об успешности перевода"
    )
    @GetMapping("/transfer-money")
    public void getMoneyTransferController(
            @Parameter(description = "id карты списания")
            @NotNull(message = "Не заполнен cardIdOne")
            @RequestHeader Long cardIdOne,
            @Parameter(description = "id карты пополнения")
            @NotNull(message = "Не заполнен cardIdTwo")
            @RequestHeader Long cardIdTwo,
            @Parameter(description = "сумма списания")
            @NotNull(message = "Не заполнен money")
            @RequestHeader BigDecimal money) {
        userService.getMoneyTransfer(cardIdOne, cardIdTwo, money);
    }

    @GetMapping("/debit-money")
    public void getDebitingMoneyController(
            @Parameter(description = "id карты списания")
            @NotNull(message = "Не заполнен cardId")
            @RequestHeader Long cardId,
            @Parameter(description = "id карты пополнения")
            @NotNull(message = "Не заполнен money")
            @RequestHeader BigDecimal money) {
        userService.getDebitingMoney(cardId, money);
    }
}
