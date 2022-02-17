package com.yeomyaloo.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    //처음 가입한 사용자인지 판단하기 위한 메소드
    Optional<User> findByEmail(String email);
}
