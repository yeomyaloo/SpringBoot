package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
//사용자 서비스를 통해서 현재 사용자를 가져오는 기능과 레지스터 기능을 구현하는 Controller를 구현하기에 앞서 UserDTO를 먼저 구현한다.
    private String token;
    private String email;
    private String username;
    private String password;
    private String id;
}
