package com.yeomyaloo.book.springboot.service.posts;

import com.yeomyaloo.book.springboot.domain.posts.Posts;
import com.yeomyaloo.book.springboot.domain.posts.PostsRepository;
import com.yeomyaloo.book.springboot.web.dto.PostsListResponseDto;
import com.yeomyaloo.book.springboot.web.dto.PostsResponseDto;
import com.yeomyaloo.book.springboot.web.dto.PostsSaveRequestDto;
import com.yeomyaloo.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// @Autowired 없이 생성자 주입이 되는 이유는??
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성해준 것.
//그렇다면 왜 이렇게 생성자 주입을 직접 쓰지 않고 어노테이션을 사용해서 진행하는가?? -> 의존성 관계 변경때마다 생성자 코드를 계속해서 수정하는 번거로움을 피하기 위해서다.
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " +id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
