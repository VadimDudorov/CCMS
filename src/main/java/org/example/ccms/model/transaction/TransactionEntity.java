package org.example.ccms.model.transaction;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction", schema = "client")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "card_id")
    Long cardId;
    @Column(name = "operation")
    String operation;
    @Column(name = "amount")
    BigDecimal amount;
    @Column(name = "timestamp_cr")
    @CreationTimestamp
    LocalDateTime timestampCr;

}
