package org.groodle.boot.service.user.repository;

import org.groodle.boot.service.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsername(String username);
    void deleteByUsername(String username);
}
