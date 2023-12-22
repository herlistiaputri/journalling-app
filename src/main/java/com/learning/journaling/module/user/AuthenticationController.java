package com.learning.journaling.module.user;

import com.learning.journaling.module.user.dto.LoginRequest;
import com.learning.journaling.module.user.dto.UserAuthenticationRequest;
import com.learning.journaling.module.user.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/registration")
    public ResponseEntity<Object> registration(@RequestBody UserAuthenticationRequest request){
        userAuthenticationService.userRegister(request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok( userAuthenticationService.login(loginRequest));
    }

}
