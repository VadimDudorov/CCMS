package org.example.ccms.service;

import lombok.RequiredArgsConstructor;
import org.example.ccms.exception.ConflictExceptionCcms;
import org.example.ccms.exception.NotFoundExceptionCcms;
import org.example.ccms.mapper.CardMapper;
import org.example.ccms.mapper.TransactionalMapper;
import org.example.ccms.mapper.UserMapper;
import org.example.ccms.model.card.CardDto;
import org.example.ccms.model.card.CardEntity;
import org.example.ccms.model.card.CardResponse;
import org.example.ccms.model.card.CardLimitDto;
import org.example.ccms.model.card.CardStatusDto;
import org.example.ccms.model.card.CardTransactionResponse;
import org.example.ccms.model.transaction.TransactionResponse;
import org.example.ccms.model.transaction.TransactionEntity;
import org.example.ccms.model.user.UserDto;
import org.example.ccms.model.user.UserEntity;
import org.example.ccms.repository.CardsRepository;
import org.example.ccms.repository.TransactionsRepository;
import org.example.ccms.repository.UsersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CardsRepository cardsRepository;
    private final CardService cardService;
    private final CardMapper cardMapper;
    private final TransactionsRepository transactionsRepository;
    private final TransactionalMapper transactionalMapper;
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Transactional
    public Long createCard(CardDto cardDto){

        String hash = cardService.hash(cardDto.number());
        List<CardEntity> byHash = cardsRepository.findByHash(hash);

        if (!byHash.isEmpty()){
            throw new ConflictExceptionCcms("Данная карта уже существует");
        }

        CardEntity cardEntity = cardMapper.createCardEntity(cardDto);
        cardEntity.setNumber(cardService.maskedNumber(cardDto.number()));
        cardEntity.setHash(hash);
        cardEntity.setEncryptedNumber(cardService.cipherEncrypt(cardDto.number()));
        CardEntity cardEntitySave = cardsRepository.save(cardEntity);

        return cardEntitySave.getId();
    }

    @Transactional
    public void updateCardStatus(CardStatusDto cardStatusDto){

        CardEntity cardEntity = cardsRepository.findById(cardStatusDto.id())
                .orElseThrow(() -> new NotFoundExceptionCcms("По указанному id = %d нет записей"
                        .formatted(cardStatusDto.id())));
        cardEntity.setStatus(cardStatusDto.status());
    }

    @Transactional
    public void updateCardLimit(CardLimitDto updateCardDto){

        CardEntity cardEntity = cardsRepository.findById(updateCardDto.id())
                .orElseThrow(() -> new NotFoundExceptionCcms("По указанному id = %d нет записей"
                        .formatted(updateCardDto.id())));
        cardEntity.setLimit(updateCardDto.limit());
        cardEntity.setNameLimit(updateCardDto.nameLimit());
    }

    @Transactional
    public void deleteCard(Long id){
        cardsRepository.deleteById(id);
    }

    public List<CardTransactionResponse> getCardTransaction(Long userId){

        List<CardEntity> cardEntities = cardsRepository.findByUserId(userId);
        if (cardEntities.isEmpty()){
            throw new NotFoundExceptionCcms("По указанному user_id = %d нет карт".formatted(userId));
        }

        List<CardTransactionResponse> cardTransactionResponses = new ArrayList<>();

        List<TransactionEntity> transactionCardId = transactionsRepository
                .findByCardIdIn(cardEntities.stream().map(CardEntity::getId).collect(Collectors.toList()));

        Map<Long, List<TransactionEntity>> transactionList = transactionCardId.stream()
                .collect(Collectors.groupingBy(TransactionEntity::getCardId));


        for (CardEntity cardEntity : cardEntities){

            List<TransactionEntity> transactionEntities = transactionList.get(cardEntity.getId());

            if (!transactionEntities.isEmpty()){
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
    public Long createUser(UserDto userDto){
       return usersRepository.save(userMapper.createUserEntity(userDto)).getUserId();
    }

    @Transactional
    public void deleteUser(Long userId){
        usersRepository.deleteById(userId);
    }
}
