package posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//클래스 내의 모든 필드의 Getter 메소드를 자동 생성
@Getter

//public Posts()와 같은 효과
@NoArgsConstructor

//테이블과 링크될 클래스임을 나타낸다.
@Entity
public class Posts {

    //해당 테이블의 PK 필드를 나타낸다. Primary key
    @Id
    //PK 생성규칙을 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //칼럼 선언이 없어도 해당 클래스의 필드는 모두 칼럼이 된다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    //해당 클래스의 빌더 패턴 클래스를 생성.
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder

    public Posts(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}

