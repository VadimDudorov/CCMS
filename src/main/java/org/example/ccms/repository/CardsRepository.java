package org.example.ccms.repository;

import org.example.ccms.model.card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardsRepository extends JpaRepository<CardEntity, Long> {

    List<CardEntity> findByHash(String hash);

    List<CardEntity> findByUserId(Long userId);
}
