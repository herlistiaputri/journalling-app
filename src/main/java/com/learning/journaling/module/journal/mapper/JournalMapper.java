package com.learning.journaling.module.journal.mapper;

import com.learning.journaling.module.journal.dto.JournalResponse;
import com.learning.journaling.module.journal.dto.JournalUserResponse;
import com.learning.journaling.module.journal.model.Journal;
import com.learning.journaling.module.user.mapper.UserMapper;
import com.learning.journaling.module.user.model.User;
import com.learning.journaling.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JournalMapper {

    private final UserMapper userMapper;

    public JournalResponse mapToResponse(Journal journal){
        return JournalResponse.builder()
                .id(journal.getId())
                .title(journal.getTitle())
                .content(journal.getContent())
                .createdAt(DateTimeUtil.parseDateTimeToString(journal.getCreatedAt()))
                .lastUpdate(DateTimeUtil.parseDateTimeToString(journal.getUpdatedAt()))
                .createdBy(journal.getCreatedBy())
                .build();
    }

    public Page<JournalResponse> mapToPage(Pageable pageable, Page<Journal> journalPage){
        return new PageImpl<>(journalPage.getContent().stream().map(this::mapToResponse).toList(),pageable, journalPage.getTotalElements());
    }

    public JournalUserResponse mapToUserResponse(User user, Page<Journal> journals, Pageable pageable){
        return JournalUserResponse.builder()
                .user(userMapper.mapToResponse(user))
                .totalPost((int) journals.getTotalElements())
                .journalResponses(mapToPage(pageable, journals))
                .build();
    }
}
