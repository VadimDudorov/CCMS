package org.example.ccms.repository;

import org.example.ccms.model.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByCardIdIn(Collection<Long> cardIds);

    @Query(value = """
                    SELECT 
                        COALESCE(sum(t.amount), 0) AS amount
                    FROM 
                        client.transactions t
                    WHERE
                        t.card_id = :cardId
                    AND 
                        t.timestamp_cr::date = CURRENT_DATE
            """, nativeQuery = true)
    BigDecimal getAmountByDay(@Param("cardId") Long cardId);

    @Query(value = """
                    SELECT 
                        COALESCE(sum(t.amount), 0) AS amount
                    FROM 
                        client.transactions t
                    WHERE
                        t.card_id = :cardId
                    AND
                        EXTRACT(YEAR FROM timestamp_cr) = EXTRACT(YEAR FROM now())
                    AND 
                        EXTRACT(MONTH FROM timestamp_cr) = EXTRACT(MONTH FROM now())
            """, nativeQuery = true)
    BigDecimal getAmountByMonth(@Param("cardId") Long cardId);
}
