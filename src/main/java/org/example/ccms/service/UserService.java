package org.example.ccms.service;

import lombok.RequiredArgsConstructor;
import org.example.ccms.exception.ConflictExceptionCcms;
import org.example.ccms.exception.NotFoundExceptionCcms;
import org.example.ccms.mapper.TransactionalMapper;
import org.example.ccms.model.card.CardEntity;
import org.example.ccms.model.card.CardTransactionResponse;
import org.example.ccms.model.transaction.TransactionEntity;
import org.example.ccms.model.transaction.TransactionResponse;
import org.example.ccms.repository.CardsRepository;
import org.example.ccms.repository.TransactionsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CardsRepository cardsRepository;
    private final TransactionsRepository transactionsRepository;
    private final TransactionalMapper transactionalMapper;

    private final String DAY_LIMIT = "day";

    public List<CardTransactionResponse> getCardTransaction(Long userId) {

        List<CardEntity> cardEntities = cardsRepository.findByUserId(userId);
        if (cardEntities.isEmpty()) {
            throw new NotFoundExceptionCcms("По указанному user_id = %d нет карт".formatted(userId));
        }

        List<CardTransactionResponse> cardTransactionResponses = new ArrayList<>();

        List<TransactionEntity> transactionCardId = transactionsRepository
                .findByCardIdIn(cardEntities.stream().map(CardEntity::getId).collect(Collectors.toList()));

        Map<Long, List<TransactionEntity>> transactionList = transactionCardId.stream()
                .collect(Collectors.groupingBy(TransactionEntity::getCardId));


        for (CardEntity cardEntity : cardEntities) {

            List<TransactionEntity> transactionEntities = transactionList.get(cardEntity.getId());

            if (!transactionEntities.isEmpty()) {
                transactionEntities.sort(Comparator.comparing(TransactionEntity::getTimestampCr));
            }

            List<TransactionResponse> transactionCardResponseList =
                    transactionalMapper.createTransactionResponse(transactionEntities);
            cardTransactionResponses.add(transactionalMapper.createCardTransactionResponse(cardEntity,
                    transactionCardResponseList));

        }

        return cardTransactionResponses;
    }

    @Transactional
    public Boolean patchCardBlocked(Long cardId) {
        return cardsRepository.updateUseCardBlocked(cardId);
    }

    public void getMoneyTransfer(Long cardIdOne, Long cardIdTwo, BigDecimal money) {

        CardEntity cardEntityOne = cardsRepository.findById(cardIdOne)
                .orElseThrow(() -> new ConflictExceptionCcms("По указанному id = %d нет карты".formatted(cardIdOne)));

        BigDecimal balance = cardEntityOne.getBalance();

        if (balance.compareTo(money) < 0) {
            throw new ConflictExceptionCcms("Не достаточно средств");
        }

        CardEntity cardEntityTwo = cardsRepository.findById(cardIdTwo)
                .orElseThrow(() -> new ConflictExceptionCcms("По указанному id = %d нет карты".formatted(cardIdTwo)));

        cardEntityOne.setBalance(balance.subtract(money));

        cardEntityTwo.setBalance(cardEntityTwo.getBalance().add(money));
    }

    public void getDebitingMoney(Long cardId, BigDecimal money) {

        CardEntity cardEntity = cardsRepository.findById(cardId)
                .orElseThrow(() -> new ConflictExceptionCcms("По указанному id = %d нет карты".formatted(cardId)));

        BigDecimal balance = cardEntity.getBalance();
        String nameLimit = cardEntity.getNameLimit();
        BigDecimal limit = cardEntity.getLimit();

        if (balance.compareTo(money) < 0) {
            throw new ConflictExceptionCcms("Не достаточно средств");
        }

        if (nameLimit == null || limit == null) {
            throw new ConflictExceptionCcms("Не заполнен лимит или время лимита");
        }
        if (nameLimit.equals(DAY_LIMIT)) {

            BigDecimal amountByDay = transactionsRepository.getAmountByDay(cardId);

            if (amountByDay.add(money).compareTo(limit) > 0) {
                throw new ConflictExceptionCcms("Лимит исчерпан");
            }
        } else {

            BigDecimal amountByMonth = transactionsRepository.getAmountByMonth(cardId);

            if (amountByMonth.add(money).compareTo(limit) > 0) {
                throw new ConflictExceptionCcms("Лимит исчерпан");
            }
        }
        cardEntity.setBalance(balance.subtract(money));
    }

}
