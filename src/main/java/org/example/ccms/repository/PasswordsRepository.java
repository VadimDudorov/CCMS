package org.example.ccms.repository;

import org.example.ccms.model.password.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordsRepository extends JpaRepository<PasswordEntity, Long> {

}
