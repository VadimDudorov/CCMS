package org.example.ccms.mapper;

import org.example.ccms.model.card.CardEntity;
import org.example.ccms.model.card.CardTransactionResponse;
import org.example.ccms.model.transaction.TransactionResponse;
import org.example.ccms.model.transaction.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionalMapper {

    @Mappings({
            @Mapping(target = "number", source = "card.number"),
            @Mapping(target = "owner", source = "card.owner"),
            @Mapping(target = "expirationDate", source = "card.expirationDate"),
            @Mapping(target = "balance", source = "card.balance"),
            @Mapping(target = "status", source = "card.status"),
            @Mapping(target = "limit", source = "card.limit"),
            @Mapping(target = "nameLimit", source = "card.nameLimit"),
            @Mapping(target = "transaction", source = "transaction")
    }
    )
    CardTransactionResponse createCardTransactionResponse(CardEntity card, List<TransactionResponse> transaction);

    List<TransactionResponse> createTransactionResponse(List<TransactionEntity> source);
}
