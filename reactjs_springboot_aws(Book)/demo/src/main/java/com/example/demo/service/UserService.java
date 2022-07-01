package com.example.demo.service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //UserRepository를 이용해 사용자 생성
    public UserEntity create(final UserEntity userEntity){
        if(userEntity == null || userEntity.getEmail() == null){
            throw new RuntimeException("Invalid arguments");
        }

        //만약 이메일이 있는 경우라면? RuntimeException을 던진다.
        final String email = userEntity.getEmail();
        if(userRepository.existsByEmail(email)){
            log.warn("Email already exist");
            throw new RuntimeException("Email already exist");
        }

        return userRepository.save(userEntity);

    }
    //로그인 시 인증에 사용할 메서드
    public UserEntity getByCredentials(final String email, final String password,
                                       PasswordEncoder encoder){

        final UserEntity originalUser = userRepository.findByEmail(email);

        //matches 메서드를 사용해 패스워드가 같은지 확인
        if(originalUser != null &&
            encoder.matches(password, originalUser.getPassword())){
            return originalUser;
        }


        return null;
    }
}
