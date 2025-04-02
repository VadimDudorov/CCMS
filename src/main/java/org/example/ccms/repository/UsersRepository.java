package org.example.ccms.repository;

import org.example.ccms.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

}
