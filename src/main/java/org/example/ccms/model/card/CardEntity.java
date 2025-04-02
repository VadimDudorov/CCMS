package org.example.ccms.model.card;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cards", schema = "client")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "number")
    String number;
    @Column(name = "owner")
    String owner;
    @Column(name = "expiration_date")
    LocalDate expirationDate;
    @Column(name = "status")
    String status;
    @Column(name = "balance")
    BigDecimal balance;
    @Column(name = "limit")
    BigDecimal limit;
    @Column(name = "name_limit")
    String nameLimit;
    @Column(name = "encrypted_number")
    String encryptedNumber;
    @Column(name = "hash")
    String hash;
}
