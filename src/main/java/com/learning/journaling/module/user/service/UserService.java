package com.learning.journaling.module.user.service;

import com.learning.journaling.configuration.exception.BaseException;
import com.learning.journaling.module.journal.repository.JournalRepository;
import com.learning.journaling.module.user.model.User;
import com.learning.journaling.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new BaseException(404, "Error Not Found", "User id not found"));
    }

    public User getByEmail(String email) {
        return (User) userRepository.findByEmail(email).orElseThrow(() -> new BaseException(404, "Error Not Found", "User id not found"));
    }

    public User profile(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
