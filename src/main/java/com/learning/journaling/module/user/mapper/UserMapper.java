package com.learning.journaling.module.user.mapper;

import com.learning.journaling.module.journal.repository.JournalRepository;
import com.learning.journaling.module.user.dto.UserResponse;
import com.learning.journaling.module.user.model.User;
import com.learning.journaling.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final JournalRepository journalRepository;
    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .lastUpdate(DateTimeUtil.parseDateTimeToString(user.getUpdatedAt()))
                .totalPost(journalRepository.countAllByUserId(user.getId()))
                .build();
    }
}
