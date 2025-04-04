package org.example.ccms.repository;

import org.example.ccms.model.card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardsRepository extends JpaRepository<CardEntity, Long> {

    List<CardEntity> findByHash(String hash);

    List<CardEntity> findByUserId(Long userId);

    @Modifying
    @Query("""
UPDATE CardEntity c
SET c.status = "Заблокирована"
WHERE c.id = :id
""")
    boolean updateUseCardBlocked(@Param("id") Long cardId);
}
