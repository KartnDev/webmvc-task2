package org.example.repository;

import org.example.domain.User;

import java.util.Optional;


public interface UserRepository {
    Optional<User> getByName(String userName);
}
