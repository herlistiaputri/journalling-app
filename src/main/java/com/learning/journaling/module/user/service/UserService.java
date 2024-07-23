package com.learning.journaling.module.user.service;

import com.learning.journaling.configuration.RequestResponseEnums;
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
        return userRepository.findById(id).orElseThrow(() -> new BaseException(RequestResponseEnums.ID_NOT_FOUND_EXCEPTION));
    }

    public User getByEmail(String email) {
        return (User) userRepository.findByEmail(email).orElseThrow(() -> new BaseException(RequestResponseEnums.ID_NOT_FOUND_EXCEPTION));
    }

    public User profile(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
