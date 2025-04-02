package org.example.ccms.repository;

import org.example.ccms.model.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TransactionsRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByCardIdIn(Collection<Long> cardIds);
}
