package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


//빌더 패턴 생성을 해주는 애너테이션
@Builder

//매개변수가 없는 생성자를 구현해준다.
@NoArgsConstructor

//클래스의 모든 멤버 변수를 매개변수로 받는 생성자를 구현
@AllArgsConstructor

// 멤버 변수의 setter/ getter 메서드 구현
@Data

//현재 클래스가 엔티티 클래스임을 명시해준다.
@Entity

//테이블 이름 지정
@Table(name = "Todo")
public class TodoEntity {
    //기본키 지정
    @Id
    // ID를 자동생성하겠다는 의미이다. generator로 어떤 방식으로 ID를 자동생성할지 지정
    @GeneratedValue(generator = "system-uuid")
    //하이버네트가 제공하는 하는 것이 아닌 나만의 generator를 사용하고 싶을 때 사용한다.
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; //이 오브젝트의 아이디
    private String userId; // 이 오브젝트를 생성한 사용자의 아이디
    private String title;
    private boolean done; // todo를 완료한 경우를 위한 멤버 변수


}
