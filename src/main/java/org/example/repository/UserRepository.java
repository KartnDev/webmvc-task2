package org.example.repository;

import org.example.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    Optional<User> getByName(String userName);
}
