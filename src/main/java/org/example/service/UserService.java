package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.exception.BadCredentialsException;
import org.example.exception.ItemNotFoundException;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service("webmvc_UserService")
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getByName(String userName) {
        return userRepository.getByName(userName).orElseThrow(ItemNotFoundException::new);
    }

    public User verifyAndLoad(String userName, String password) {
        final var loadedByName = getByName(userName);
        if (!loadedByName.getPassword().equals(password)) {
            throw new BadCredentialsException("Password to current userName is wrong");
        }
        return loadedByName;
    }

}
