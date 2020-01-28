package org.dhzm.book.springboot.service;

import lombok.RequiredArgsConstructor;
import org.dhzm.book.springboot.domain.posts.PostsRepository;
import org.dhzm.book.springboot.web.dto.PostsSaveRequestDto;
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
}

/*
    Service layer
    - 트랜잭션과 도메인 간의 순서만 보장

    !! 기존 트랜잭션 스크립트 방식을 서비스에서 Domain으로 변경
    - 비지니스 처리를 Domain에서 처리
 */