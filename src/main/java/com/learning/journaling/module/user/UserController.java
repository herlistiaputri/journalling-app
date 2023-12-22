package com.learning.journaling.module.user;

import com.learning.journaling.module.user.dto.UserResponse;
import com.learning.journaling.module.user.mapper.UserMapper;
import com.learning.journaling.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(){

        return ResponseEntity.ok(userMapper.mapToResponse(userService.profile()));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id){
        return ResponseEntity.ok(userMapper.mapToResponse(userService.getById(id)));
    }

    @GetMapping("/get-by-email/{id}")
    public ResponseEntity<Object> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(userMapper.mapToResponse(userService.getByEmail(email)));
    }
}
