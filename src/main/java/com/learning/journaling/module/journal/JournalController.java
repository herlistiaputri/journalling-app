package com.learning.journaling.module.journal;

import com.learning.journaling.module.journal.dto.JournalRequest;
import com.learning.journaling.module.journal.dto.JournalResponse;
import com.learning.journaling.module.journal.dto.JournalUserResponse;
import com.learning.journaling.module.journal.mapper.JournalMapper;
import com.learning.journaling.module.journal.service.JournalService;
import com.learning.journaling.module.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;
    private final JournalMapper journalMapper;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody JournalRequest journalRequest){
        journalService.create(journalRequest);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalResponse> getById(@PathVariable String id){
        return ResponseEntity.ok(journalMapper.mapToResponse(journalService.getById(id)));
    }

    @GetMapping("/page")
    public ResponseEntity<JournalUserResponse> getUserJournal(Pageable pageable){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(journalMapper.mapToUserResponse(user, journalService.getByUser(pageable, user), pageable));
    }

}
