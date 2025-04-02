package org.example.ccms.model.card;

import org.example.ccms.model.transaction.TransactionResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CardTransactionResponse(String number,
                                      String owner,
                                      LocalDate expirationDate,
                                      BigDecimal balance,
                                      String status,
                                      BigDecimal limit,
                                      String nameLimit,
                                      List<TransactionResponse> transaction
) {
}
