package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//빌더 패턴 생성을 해주는 애너테이션
@Builder

//매개변수가 없는 생성자를 구현해준다.
@NoArgsConstructor

//클래스의 모든 멤버 변수를 매개변수로 받는 생성자를 구현
@AllArgsConstructor

// 멤버 변수의 setter/ getter 메서드 구현
@Data
public class TodoEntity {
    private String id; //이 오브젝트의 아이디
    private String userId; // 이 오브젝트를 생성한 사용자의 아이디
    private String title;
    private boolean done; // todo를 완료한 경우를 위한 멤버 변수


}
