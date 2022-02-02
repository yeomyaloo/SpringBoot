package com.yeomyaloo.book.springboot.web.domain.posts;

import posts.Posts;
import posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;


    //단위 테스트가 끝날 때마다 수행되는 메소드를 지정.
    // 매 테스트가 끝날 때마다 모두 지우는 작업을 해준다는 의미
    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테아블 posts에 insert/update 쿼리를 실행한다.
        postsRepository.save(Posts.builder()
                        .title(title)
                        .author("yeomyaloo@naver.com")
                        .content(content)
                        .build());

        //when
        //테이블 posts에 있는 모든 데이터를 조회해오는 메소드
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);


    }

}
