package com.learning.journaling.module.user.util;


import com.learning.journaling.configuration.exception.BaseException;
import com.learning.journaling.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@RequiredArgsConstructor
public class UserValidation {
    private final UserRepository userRepository;

    public void emailExistedValidation(String email){
        if(userRepository.existsByEmail(email)){
            throw new BaseException(400, "Error", "Email has been used");
        }
    }
    public void checkEmailPattern(String email){
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) {
            throw new BaseException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "Invalid email");
        }
    }

    public void  checkPassword(String password){
        if(password.length() < 8) {
            throw new BaseException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "Invalid password");
        }
    }

    public void  checkPin(String pin){
        if(pin.length() != 4) {
            throw new BaseException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "Invalid pin");
        }
    }


}
