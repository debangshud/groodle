package org.dasgupta.sample.springboot.jpa.repository;

import org.dasgupta.sample.springboot.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
