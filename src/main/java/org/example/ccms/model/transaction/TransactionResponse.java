package org.example.ccms.model.transaction;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TransactionResponse(String operation,
                                  String amount,
                                  LocalDateTime timestampCr) {
}
