package org.dhzm.book.springboot.service;

import lombok.RequiredArgsConstructor;
import org.dhzm.book.springboot.domain.posts.Posts;
import org.dhzm.book.springboot.domain.posts.PostsRepository;
import org.dhzm.book.springboot.web.dto.PostsResponseDto;
import org.dhzm.book.springboot.web.dto.PostsSaveRequestDto;
import org.dhzm.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}

/*
    Service layer
    - 트랜잭션과 도메인 간의 순서만 보장

    !! 기존 트랜잭션 스크립트 방식을 서비스에서 Domain으로 변경
    - 비지니스 처리를 Domain에서 처리

    Update 기능에 쿼리를 날리는 부분이 없는 이유
    - JPA의 영속성 컨텍스트 때문
    - 엔티티를 영구 저장하는 환경
    - 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지 -> 트랜잭션이 끝나는 시점에
        해당 테이블에 변경분을 반영 (Update 쿼리를 날릴 필요가 없음 | ** 더티 체킹 **)

 */